package thread.concurrency.three;
/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午9:08:13
  * @version 1.0 
  */
public class Job implements Runnable{

	private PrintQueue printQueue;
	
	public Job(PrintQueue printQueue) {
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
