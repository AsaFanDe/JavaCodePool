package thread.concurrency.three;
/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午9:39:36
  * @version 1.0 
  */
public class Job2 implements Runnable{

	private PrintQueue2 printQueue;
	
	public Job2(PrintQueue2 printQueue) {
		this.printQueue = printQueue;
	}
	
	@Override
	public void run() {
		System.out.printf("%s: Going to print a job\n", 
				Thread.currentThread().getName());
		printQueue.printJob(new Object());
		System.out.printf("%s: The document has been printed\n", 
				Thread.currentThread().getName());
	}

}
