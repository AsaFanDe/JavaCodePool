package thread.concurrency.two;
/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午7:12:41
  * @version 1.0 
  */
public class Producer2 implements Runnable{
	
	private FileMock mock;
	
	private Buffer buffer;

	public Producer2(FileMock mock, Buffer buffer) {
		super();
		this.mock = mock;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		buffer.setPendingLines(true);
		while (mock.hasMoreLines()) {
			String line = mock.getLine();
			buffer.insert(line);
		}
		buffer.setPendingLines(false);
	}

}
