package thread.concurrency.two;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午1:43:17
  * @version 1.0 
  */
public class PrintQueue {
	
	public final Lock queueLock = new ReentrantLock();
	
	public void printJob(Object document){
		queueLock.lock();
		try {
			long duration = (long)(Math.random() * 10000);
			System.out.printf(Thread.currentThread().getName()+ 
					"PrintQueue: Println a Job during " + (duration/1000) + " seconds");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			queueLock.unlock();
		}
	}

}
