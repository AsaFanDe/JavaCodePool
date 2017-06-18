package thread.concurrency.one;

import java.util.Random;

public class MyTask implements Runnable{

	@Override
	public void run() {
		int result;
		Random random = new Random(Thread.currentThread().getId());
		while (true) {
			result = 1000/(int)(random.nextDouble()*1000);
			System.out.printf("%s : %d\n", Thread.currentThread().getId(),result);
			return;
		}
		
	}

}
