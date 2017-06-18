import org.junit.Test;

import thread.concurrency.three.Job;
import thread.concurrency.three.Job2;
import thread.concurrency.three.PrintQueue;
import thread.concurrency.three.PrintQueue2;

/** * @author  作者 : 范德胜
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
}
