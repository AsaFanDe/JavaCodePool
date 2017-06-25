package thread.concurrency.test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;

import org.junit.Test;

import thread.concurrency.three.Consumer;
import thread.concurrency.three.FileSearch;
import thread.concurrency.three.Grouper;
import thread.concurrency.three.Job;
import thread.concurrency.three.Job2;
import thread.concurrency.three.MatrixMock;
import thread.concurrency.three.MyPhaser;
import thread.concurrency.three.Participant;
import thread.concurrency.three.PrintQueue;
import thread.concurrency.three.PrintQueue2;
import thread.concurrency.three.Producer;
import thread.concurrency.three.Results;
import thread.concurrency.three.Searcher;
import thread.concurrency.three.Student;
import thread.concurrency.three.Videoconference;

/** 
  * 这里的测试代码均来自《Java7并发编程实战手册》
  * 第三章  线程同步辅助类
  * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午7:37:29
  * @version 1.0 
  */
public class ThreeTest {

	/**
	 * 3.2 资源的并发访问控制
	 */
	@Test
	public void mainTest1(){
		PrintQueue printQueue = new PrintQueue();
		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue),"Thread"+i);
		}
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}
	
	/**
	 * 3.3 资源的多副本的并发访问控制
	 */
	@Test
	public void mainTest2(){
		PrintQueue2 printQueue = new PrintQueue2();
		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job2(printQueue),"Thread"+i);
		}
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}
	
	/**
	 * 3.4 等待多个并发的事件的完成
	 */
	@Test
	public void mainTest3(){
		Videoconference conference = new Videoconference(10);
		Thread thread = new Thread(conference);
		thread.start();
		for (int i = 0; i < 10; i++) {
			Participant p = new Participant(conference, "Participant" + i);
			Thread t = new Thread(p);
			t.start();
		}
	}
	
	/**
	 * 3.5 在集合点的同步
	 */
	@Test
	public void mainTest4(){
		final int ROWS = 10000;
		final int NUMBERS = 1000;
		final int SEARCH = 5;
		final int PARTICIPANTS = 5;
		final int LINES_PARTICIPANTS = 2000;
		MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);
		Results results = new Results(ROWS);
		Grouper grouper = new Grouper(results);
		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS,grouper);
		Searcher searcher[] = new Searcher[PARTICIPANTS];
		for (int i = 0; i < PARTICIPANTS; i++) {
			searcher[i] = new Searcher(i * LINES_PARTICIPANTS, 
					i * LINES_PARTICIPANTS + LINES_PARTICIPANTS, mock, results, 5, barrier);
			Thread thread = new Thread(searcher[i]);
			thread.start();
		}
		System.out.printf("Main: The main thread has finished.\n");
	}
	
	/**
	 * 3.6 并发阶段任务的运行
	 */
	@Test
	public void mainTest5(){
		Phaser phaser = new Phaser(3);
		FileSearch system = new FileSearch("C:\\Window", "log", phaser);
		FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
		FileSearch documents = new FileSearch("C:\\Documents And Setting", "log", phaser);
		Thread systemThread = new Thread(system, "system");
		systemThread.start();
		Thread appsThread = new Thread(apps, "Apps");
		appsThread.start();
		Thread documentsThread = new Thread(documents, "documents");
		documentsThread.start();
		try {
			systemThread.join();
			appsThread.join();
			documentsThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Terminated: "+ phaser.isTerminated());
	}
	
	/**
	 * 3.7  并发阶段任务中的阶段切换
	 */
	@Test
	public void mainTest6(){
		MyPhaser phaser = new MyPhaser();
		Student students[] = new Student[5];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(phaser);
			phaser.register();
		}
		Thread threads[] = new Thread[students.length];
		for (int i = 0; i < students.length; i++) {
			threads[i] = new Thread(students[i], "Student" +i);
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Mian: The Phaser has finished: %s.\n", phaser.isTerminated());
	}
	
	/**
	 * 并发任务间的数据交换
	 */
	@Test
	public void mainTest7(){
		List<String> buffer1 = new ArrayList<String>();
		List<String> buffer2 = new ArrayList<String>();
		
		Exchanger<List<String>> exchanger = new Exchanger<List<String>>();
		
		Producer producer = new Producer(buffer1, exchanger);
		Consumer consumer = new Consumer(buffer2, exchanger);
		
		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);
		threadProducer.start();
		threadConsumer.start();
	}
	
	
}
