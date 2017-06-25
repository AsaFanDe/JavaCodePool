package thread.concurrency.four;

import java.sql.Time;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月25日 下午8:30:14
  * @version 1.0 
  */
public class Task2 implements Callable<Result>{
	
	private String name;
	
	public Task2(String name) {
		this.name = name;
	}

	@Override
	public Result call() throws Exception {
		System.out.printf("%s: Starting\n", this.name);
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Waitting %d seconds for results.\n", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int value = 0;
		for (int i = 0; i < 5; i++) {
			value += (int)(Math.random() * 100);
		}
		Result result = new Result();
		result.setName(name);
		result.setValue(value);
		System.out.println(this.name+": Ends");
		return result;
	}

}
