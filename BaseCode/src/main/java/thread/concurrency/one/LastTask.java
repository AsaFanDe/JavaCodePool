package thread.concurrency.one;

import java.util.concurrent.TimeUnit;

public class LastTask implements Runnable{

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
