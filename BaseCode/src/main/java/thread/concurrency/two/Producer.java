package thread.concurrency.two;
/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午1:24:51
  * @version 1.0 
  */
public class Producer implements Runnable{
	
	private EventStorage storage;
	
	public Producer(EventStorage storage) {
		this.storage = storage;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.get();
		}
	}

}
