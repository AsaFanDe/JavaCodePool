package thread.concurrency.two;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午6:51:07
  * @version 1.0 
  */
public class Buffer {
	
	private LinkedList<String> buffer;
	
	private int maxSize;
	
	private ReentrantLock lock;
	
	private Condition lines;
	
	private Condition space;
	
	private boolean pendingLines;

	public Buffer(int maxSize) {
		super();
		this.buffer = new LinkedList<String>();
		this.maxSize = maxSize;
		this.lock = new ReentrantLock();
		this.lines = lock.newCondition();
		this.space = lock.newCondition();
		this.pendingLines = true;
	}
	
	public void insert(String line){
		lock.lock();
		try {
			while (buffer.size() == maxSize) {
				space.await();
				
			}
			buffer.offer(line);
			System.out.printf("%s: Inserted Line: %d\n", 
					Thread.currentThread().getName(),buffer.size());
			lines.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

	public String get(){
		String line = null;
		lock.lock();
		try {
			while (buffer.size() == 0 && hasPendingLines()) {
				lines.await();
			}
			if (hasPendingLines()) {
				line = buffer.poll();
				System.out.printf("%s: Line Readed： %d\n",
						Thread.currentThread().getName(),buffer.size());
				space.signalAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return line;
	}

	public void setPendingLines(boolean pendingLines){
		this.pendingLines = pendingLines;
		
	}
	public boolean hasPendingLines() {
		return pendingLines || buffer.size() > 0;
	}
	
	
}
