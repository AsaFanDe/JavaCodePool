package thread.concurrency.two;

import java.util.Random;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午7:15:40
  * @version 1.0 
  */
public class Consumer2 implements Runnable{
	
	private Buffer buffer;
	
	public Consumer2(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (buffer.hasPendingLines()) {
			String line = buffer.get();
			processLine(line);
		}
	}

	public void processLine(String line) {
		try {
			Random random = new Random();
			Thread.sleep(random.nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
