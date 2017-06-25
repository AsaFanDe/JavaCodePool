package thread.concurrency.test;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;


import org.junit.Test;

import aj.org.objectweb.asm.Label;
import thread.concurrency.one.Calculator;
import thread.concurrency.one.CleanerTask;
import thread.concurrency.one.DataSourcesLoader;
import thread.concurrency.one.Event;
import thread.concurrency.one.ExceptionHandler;
import thread.concurrency.one.FileClock;
import thread.concurrency.one.FileSearch;
import thread.concurrency.one.LastTask;
import thread.concurrency.one.MyTask;
import thread.concurrency.one.MyThreadFactory;
import thread.concurrency.one.MyThreadGroup;
import thread.concurrency.one.NetworkConnectionsLoader;
import thread.concurrency.one.PrimeGenerator;
import thread.concurrency.one.Result;
import thread.concurrency.one.SearchTask;
import thread.concurrency.one.Task;
import thread.concurrency.one.UnsafeTask;
import thread.concurrency.one.WriterTask;

/**
 * 这里的测试代码均来自《Java7并发编程实战手册》
 * 第一章  线程管理
 * @author Administrator
 *
 */
public class OneTest {
	
	/**
	 * 1.2线程的创建和运行
	 */
	@Test
	public void mainTest1(){
		for(int i = 1; i <= 10; i++){
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}
	
	/**
	 * 1.3线程信息的获取和设置
	 */
	@Test
	public void mainTest2(){
		Thread threads[] = new Thread[10];
		Thread.State status[] = new Thread.State[10];
		for(int i=0; i<10 ;i++){
			threads[i] = new Thread(new Calculator(i));
			if (i%2 == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			}else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread"+i);
		}
		try (FileWriter file = new FileWriter("E:\\text.txt");
				PrintWriter pw = new PrintWriter(file);){
			for(int i = 0; i<10; i++ ){
				pw.println("Main : Status of Thread "+i+" : "
						+threads[i].getState());
				status[i] = threads[i].getState();
			}
			for(int i = 0; i<10; i++ ){
				threads[i].start();
			}
			
			boolean finish = false;
			while (!finish) {
				for(int i = 0; i<10; i++){
					if (threads[i].getState() != status[i]) {
						this.writeThreadInfo(pw,threads[i],status[i]);
						status[i]=threads[i].getState();
					}
				}
				finish = true;
				for(int i = 0; i<10; i++){
					finish = finish && (threads[i].getState()==State.TERMINATED);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
		pw.printf("Main : Id %d - %s\n", thread.getId(),thread.getName());
		pw.printf("Main : Priority: %d\n", thread.getPriority());
		pw.printf("Main : Old State: %s\n", state);
		pw.printf("Main : New State: %s\n", thread.getState());
		pw.printf("Main:*****************\n");
	}
	
	/**
	 * 1.4线程的中断
	 */
	@Test
	public void mainTest3(){
		Thread task = new PrimeGenerator();
		task.start();
		try{
			Thread.sleep(5000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 1.5线程中断控制
	 */
	@Test
	public void mainTest4(){
		FileSearch search = new FileSearch("C:\\", "autoexec.bat");
		Thread thread = new Thread(search);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
	
	/**
	 * 1.6线程的休眠与恢复
	 */
	@Test
	public void mainTest5(){
		FileClock fileClock = new FileClock();
		Thread thread = new Thread(fileClock);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();	
	}
	
	/**
	 * 1.7等待的线程终止
	 */
	@Test
	public void mainTest6(){
		DataSourcesLoader loader = new DataSourcesLoader();
		Thread thread = new Thread(loader,"DataSourcesLoader");
		NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
		Thread thread2 = new Thread(ncLoader);
		thread.start();
		thread2.start();
		try {
			thread.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Configuration has been loaded:%s\n", new Date());
	}

	/**
	 * 1.8守护线程的创建和运行
	 */
	public void mainTest7(){
		Deque<Event> deque = new ArrayDeque<Event>();
		WriterTask writerTask = new WriterTask(deque);
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(writerTask);
			thread.start();
		}
		CleanerTask cleanerTask = new CleanerTask(deque);
		cleanerTask.start();
	}
	
	/**
	 * 1.9 线程中不可控异常的处理
	 */
	@Test
	public void mainTest8(){
		Task task = new Task();
		Thread thread = new Thread(task);
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();
	}
	
	/**
	 * 1.10线程局部变量的使用
	 */
	@Test
	public void mainTest9(){
		UnsafeTask task = new UnsafeTask();
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(task);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 1.11 线程的分组
	 */
	@Test
	public void mainTest10(){
		ThreadGroup group = new ThreadGroup("seracher");
		Result result = new Result();
		SearchTask searchTask = new SearchTask(result);
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Number of Threads: %d\n", group.activeCount());
		System.out.printf("Information about the Thread Group\n");
		group.list();
		
		Thread[] threads = new Thread[group.activeCount()];
		group.enumerate(threads);
		for(int i = 0; i < group.activeCount(); i++){
			System.out.printf("Thread %s : %s\n", threads[i].getName(),threads[i].getState());
		}
		waitFinish(group);
		group.interrupt();
	}

	private void waitFinish(ThreadGroup group) {
		while (group.activeCount() > 0) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 1.12 线程组中不可控异常处理
	 */
	@Test
	public void mainTest11(){
		MyThreadGroup myThreadGroup = new MyThreadGroup("MuThreadGroup");
		MyTask task = new MyTask();
		for(int i = 0; i < 2; i++){
			Thread thread = new Thread(myThreadGroup,task);
			thread.start();
		}
	}
	
	/**
	 * 1.13 使用工厂类创建线程
	 */
	@Test
	public void mainTest12(){
		MyThreadFactory myThreadFactory = new MyThreadFactory("MyThreadFacory");
		LastTask task = new LastTask();
		Thread thread;
		System.out.printf("Starting the Threads\n");
		for (int i = 0; i < 10; i++) {
			thread = myThreadFactory.newThread(task);
			thread.start();
		}
		System.out.printf("Factory start :\n");
		System.out.printf("%s\n", myThreadFactory.getStats());
	}
}
