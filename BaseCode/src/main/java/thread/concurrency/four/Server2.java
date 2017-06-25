package thread.concurrency.four;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月23日 下午11:57:46
  * @version 1.0 
  */
public class Server2 {

	private ThreadPoolExecutor executor;
	
	public Server2() {
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	}
	
	public void executeTask(Task task){
		System.out.printf("Server: A new task has arrive\n");
		executor.execute(task);
		System.out.printf("Server: pool Size: %d\n", executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
		System.out.printf("Server: Task Count: %d", executor.getTaskCount());
	}
	
	public void endServer(){
		executor.shutdown();
	}
}
