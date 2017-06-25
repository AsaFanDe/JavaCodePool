package thread.concurrency.test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import thread.concurrency.four.FactorialCalculator;
import thread.concurrency.four.Server;
import thread.concurrency.four.Server2;
import thread.concurrency.four.Task;
import thread.concurrency.four.Task2;
import thread.concurrency.four.TaskValidator;
import thread.concurrency.four.UserValidator;


/** 
  * 这里的测试代码均来自《Java7并发编程实战手册》
  * 第四章 线程执行器
  * @author  作者 : 范德胜
  * @date 创建时间：2017年6月23日 下午8:06:48
  * @version 1.0 
  */
public class fourTest {
	
	/**
	 * 4.2 创建线程执行器
	 */
	@Test
	public void mainTest1(){
		Server server = new Server();
		for (int i = 0; i < 100; i++) {
			Task task = new Task("Task "+i);
			server.executeTask(task);
		}
		server.endServer();
	}
	
	/**
	 * 4.3 创建固定大小的线程执行器
	 */
	@Test
	public void mainTest2(){
		Server2 server = new Server2();
		for (int i = 0; i < 100; i++) {
			Task task = new Task("Task "+i);
			server.executeTask(task);
		}
		server.endServer();
	}
	
	/**
	 * 4.4 在执行器中执行任务并返回结果
	 */
	@Test
	public void mainTest3(){
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		List<Future<Integer>> resultList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			Integer number = random.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(number);
			Future<Integer> result = executor.submit(calculator);
			resultList.add(result);
		}
		do {
			System.out.printf("Main: Number of Completed Tasks: %d\n", 
					executor.getCompletedTaskCount());
			for (int i = 0; i < resultList.size(); i++) {
				Future<Integer> result = resultList.get(i);
				System.out.printf("Main: Task %d: %s\n", i, result.isDone());
			}
			try {
				TimeUnit.MICROSECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (executor.getCompletedTaskCount() < resultList.size());
		
		System.out.printf("Main: Results\n");
		for (int i = 0; i < resultList.size(); i++) {
			Future<Integer> result = resultList.get(i);
			Integer number = null;
			try {
				number = result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.printf("Main: Task %d: %d\n", i, number);
		}
		executor.shutdown();
	}
	
	/**
	 * 4.5 运行多个任务并处理第一个结果
	 */
	@Test
	public void mainTest4(){
		String username = "test";
		String password = "test";
		
		UserValidator ldapValidator = new UserValidator("LADP");
		UserValidator dbValidator = new UserValidator("DataBase");
		
		TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
		TaskValidator dbTask = new TaskValidator(dbValidator, username, password);
		
		List<TaskValidator> taskList = new ArrayList<>();
		taskList.add(ldapTask);
		taskList.add(dbTask);
		
		ExecutorService executor = (ExecutorService)Executors.newCachedThreadPool();
		String result;
		try {
			result = executor.invokeAny(taskList);
			System.out.printf("Main: Result: %s\n", result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		System.out.println("Main: End of the Executor");
		
	}

	/**
	 * 4.6 运行多个任务并处理所有结果
	 */
	@Test
	public void mainTest5(){
		/*ExecutorService executor = (ExecutorService)Executors.newCachedThreadPool();
		List<Task2> taskList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Task2 task = new Task2(i);
			taskList.add(task2);
		}*/
	}
	
	/**
	 * 4.7 在执行器中延时执行任务
	 */
	
	
	
	
	
	
	
	
}
