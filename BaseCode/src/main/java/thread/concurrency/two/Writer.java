package thread.concurrency.two;
/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午2:18:58
  * @version 1.0 
  */
public class Writer implements Runnable{

	private PricesInfo pricesInfo;
	
	public Writer(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.printf("Write: Attempt to modify the price.\n");
			pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
			System.out.printf("Writer: prices have been modified.\n");
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
