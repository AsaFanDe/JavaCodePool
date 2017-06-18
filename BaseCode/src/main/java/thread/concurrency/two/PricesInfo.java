package thread.concurrency.two;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月18日 下午2:01:28
  * @version 1.0 
  */
public class PricesInfo {
	
	private double price1;
	
	private double price2;
	
	private ReadWriteLock lock;

	public PricesInfo() {
		super();
		this.price1 = 1.0;
		this.price2 = 2.0;
		this.lock = new ReentrantReadWriteLock();
	}
	
	public double getPrice1(){
		lock.readLock().lock();
		double value = price1;
		lock.readLock().unlock();
		return value;
	}
	
	public double getPrice2(){
		lock.readLock().lock();
		double value = price2;
		lock.readLock().unlock();
		return value;
	}
	
	public void setPrices(double price1, double price2){
		lock.writeLock().lock();
		this.price1 = price1;
		this.price2 = price2;
		lock.writeLock().unlock();
	}
}
