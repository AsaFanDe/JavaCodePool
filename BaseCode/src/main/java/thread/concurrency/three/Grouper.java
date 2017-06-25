package thread.concurrency.three;
/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月19日 下午5:49:06
  * @version 1.0 
  */
public class Grouper implements Runnable{
	
	private Results results;
	
	public Grouper(Results results) {
		this.results = results;
	}

	@Override
	public void run() {
		int finalResults = 0;
		System.out.printf("Grouper: Processing results...\n");
		int data[] = results.getData();
		for (int number : data) {
			finalResults +=	number;
		}
		System.out.printf("Grouoer: Total result: %d.\n", finalResults);
	}

}
