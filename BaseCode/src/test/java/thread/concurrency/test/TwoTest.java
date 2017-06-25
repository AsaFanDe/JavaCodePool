package thread.concurrency.test;
import org.junit.Test;

import thread.concurrency.two.Account;
import thread.concurrency.two.Bank;
import thread.concurrency.two.Buffer;
import thread.concurrency.two.Cinema;
import thread.concurrency.two.Company;
import thread.concurrency.two.Consumer;
import thread.concurrency.two.Consumer2;
import thread.concurrency.two.EventStorage;
import thread.concurrency.two.FileMock;
import thread.concurrency.two.Job;
import thread.concurrency.two.Job2;
import thread.concurrency.two.PricesInfo;
import thread.concurrency.two.PrintQueue;
import thread.concurrency.two.PrintQueue2;
import thread.concurrency.two.Producer;
import thread.concurrency.two.Producer2;
import thread.concurrency.two.Reader;
import thread.concurrency.two.TicketOffice1;
import thread.concurrency.two.TicketOffice2;
import thread.concurrency.two.Writer;

/** 
  * 这里的测试代码均来自《Java7并发编程实战手册》
  * 第二章   线程同步基础
  * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 上午10:17:25
  * @version 1.0 
  */
public class TwoTest {
	
	/**
	 * 2.2 用synchronized实现同步的方法
	 */
	@Test
	public void mainTest1(){
		Account account = new Account();
		account.setBalance(1000);
		Company company = new Company(account);
		Thread companyThread = new Thread(company);
		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);
		System.out.printf("Acount : Initial Balance: %f\n", account.getBalance());
		companyThread.start();
		bankThread.start();
		try {
			companyThread.join();
			bankThread.join();
			System.out.printf("Account : Final Balance: %f\n", account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 2.3 使用非依赖属性实现同步
	 */
	@Test
	public void mainTest2(){
		Cinema cinema = new Cinema();
		TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
		Thread thread1 = new Thread(ticketOffice1);
		TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
		Thread thread2 = new Thread(ticketOffice2);
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Room 1 Vacancies: %d\n", cinema.getVacanciesCinema1());
		System.out.printf("Room 2 Vacancies: %d\n", cinema.getVacanciesCinema2());
	}
	
	/**
	 * 2.4 在同步代码中使用条件
	 */
	@Test
	public void mainTest3(){
		EventStorage storage = new EventStorage();
		Producer producer = new Producer(storage);
		Thread thread1 = new Thread(producer);
		Consumer consumer = new Consumer(storage);
		Thread thread2 = new Thread(consumer);
		thread2.start();
		thread1.start();
	}
	
	/**
	 * 2.5 实现锁实现同步
	 */
	@Test
	public void mainTest4(){
		PrintQueue printQueue = new PrintQueue();
		Thread thread[] = new Thread[10];
		for(int i = 0; i < 10; i++){
			thread[i] = new Thread(new Job(printQueue),"Thread "+i);
		}
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}
	
	/**
	 * 2.6 使用读写锁实现同步数据访问
	 */
	@Test
	public void mainTest5(){
		PricesInfo pricesInfo = new PricesInfo();
		Reader readers[] = new Reader[5];
		Thread threadReader[] = new Thread[5];
		for (int i = 0; i < 5; i++) {
			readers[i] = new Reader(pricesInfo);
			threadReader[i] = new Thread(readers[i]);
		}
		Writer writer = new Writer(pricesInfo);
		Thread writerThread = new Thread(writer);
		for(int i = 0; i < 5; i++){
			threadReader[i].start();
		}
		writerThread.start();
	}
	
	/**
	 * 2.7 修改锁的公平性
	 */
	@Test
	public void mainTest6(){
		PrintQueue2 printQueue = new PrintQueue2();
		Thread thread[] = new Thread[10];
		for(int i = 0; i < 10; i++){
			thread[i] = new Thread(new Job2(printQueue),"Thread "+i);
		}
		for(int i = 0; i < 10; i++){
			thread[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 2.8 在锁中使用多条件
	 */
	@Test
	public void mainTest7(){
		FileMock mock = new FileMock(100, 10);
		Buffer buffer = new Buffer(20);
		Producer2 producer = new Producer2(mock, buffer);
		Thread producerThread = new Thread(producer,"Producer");
		Consumer2 consumer2[] = new Consumer2[3];
		Thread threadConsumers[] = new Thread[3];
		for (int i = 0; i < 3; i++) {
			consumer2[i] = new Consumer2(buffer);
			threadConsumers[i] = new Thread(consumer2[i],"consumer"+ i);
		}
		producerThread.start();
		for (int i = 0; i < 3; i++) {
			threadConsumers[i].start();
		}
	}
	

}
