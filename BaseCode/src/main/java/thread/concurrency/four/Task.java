package thread.concurrency.four;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月23日 下午8:48:27
  * @version 1.0 
  */
public class Task implements Runnable{
	
	private Date initDate;
	
	private String name;
	
	public Task(String name) {
		initDate = new Date();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.printf("%s: Task %s: Created on: %s\n", 
				Thread.currentThread().getName(), name, initDate);
		System.out.printf("%s: Task %s: Started on: %s\n", 
				Thread.currentThread().getName(), name, new Date());
		try {
			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Task %s: Doing a task during %d second\n", 
					Thread.currentThread().getName(),name,duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Task %s: Finished on: %s\n", 
				Thread.currentThread().getName(), name, new Date());
	}

}
