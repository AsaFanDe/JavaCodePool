package thread.concurrency.three;

import java.util.concurrent.CountDownLatch;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午9:45:00
  * @version 1.0 
  */
public class Videoconference implements Runnable{
	
	private final CountDownLatch controller;
	

	public Videoconference(int number) {
		this.controller = new CountDownLatch(number);
	}

	public void arrive(String name){
		System.out.printf("%s has arrive", name);
		controller.countDown();
		System.out.printf("VideoConference: waiting for %d participants.\n", 
				controller.getCount());
	}

	@Override
	public void run() {
		System.out.printf("VideoConference Initialization: %d participants.\n", 
				controller.getCount());
		try {
			controller.await();
			System.out.printf("VideoConference: All the participants have come\n");
			System.out.printf("VideoConference: Let's start...\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
