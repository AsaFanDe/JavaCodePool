package thread.concurrency.two;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午2:29:13
  * @version 1.0 
  */
public class PrintQueue2 {
	
	private Lock queueLock = new ReentrantLock(true);
	
	public void printJob(Object documate){
		queueLock.lock();
		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() 
					+": PrintQueue: Printing a Job during "+(duration/1000)+" seconds");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			queueLock.lock();
		}
		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() 
					+": PrintQueue: Printing a Job during "+(duration/1000)+" seconds");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			queueLock.unlock();
		}	
	}

}
