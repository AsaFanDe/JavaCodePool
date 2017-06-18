package thread.concurrency.one;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourcesLoader implements Runnable{

	@Override
	public void run() {
		System.out.printf("Begining Data sources loading: %s\n", new Date());
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Begining Data sources loading: %s\n", new Date());
	}

}
