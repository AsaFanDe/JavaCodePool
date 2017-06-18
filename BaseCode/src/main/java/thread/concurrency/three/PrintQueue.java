package thread.concurrency.three;

import java.util.concurrent.Semaphore;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午7:39:28
  * @version 1.0 
  */
public class PrintQueue {
	
	private final Semaphore semaphore;

	public PrintQueue() {
		this.semaphore = new Semaphore(1);
	}

	public void printJob(Object document){
		try {
			semaphore.acquire();
			long duration = (long) (Math.random()*10);
			System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", 
					Thread.currentThread().getName(),duration);
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}
