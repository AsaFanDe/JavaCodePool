package thread.concurrency.two;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 上午11:26:54
  * @version 1.0 
  */
public class EventStorage {
	
	private int maxSize;
	
	private List<Date> storage;

	public EventStorage() {
		this.maxSize = 10;
		this.storage = new LinkedList<>();
	}
	
	public synchronized void set(){
		while (storage.size() == maxSize){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			storage.add(new Date());
			System.out.printf("Set: %d\n", storage.size());
			notifyAll();
		}
	}
	
	public synchronized void get(){
		while (storage.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Get: %d: %s", storage.size(),((LinkedList<?>)storage).poll());
		notifyAll();
	}

}
