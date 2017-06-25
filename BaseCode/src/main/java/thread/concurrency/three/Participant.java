package thread.concurrency.three;

import java.util.concurrent.TimeUnit;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月19日 下午4:50:33
  * @version 1.0 
  */
public class Participant implements Runnable{
	
	private Videoconference conference;
	
	private String name;

	public Participant(Videoconference conference, String name) {
		this.conference = conference;
		this.name = name;
	}

	@Override
	public void run() {
		long duration  = (long) (Math.random() * 10);
		try {
			TimeUnit.SECONDS.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		conference.arrive(name);
	}

}
