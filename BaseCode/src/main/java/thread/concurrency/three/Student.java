package thread.concurrency.three;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月23日 下午4:44:38
  * @version 1.0 
  */
public class Student implements Runnable{
	
	private Phaser phaser;

	public Student(Phaser phaser) {
		this.phaser = phaser;
	}

	@Override
	public void run() {
		System.out.printf("%s: Has arrived to do the exam.%s\n",
				Thread.currentThread().getName(),new Date());
		phaser.arriveAndAwaitAdvance();
		System.out.printf("%s: Is going to do the first exercise.%s\n", 
				Thread.currentThread().getName(),new Date());
		doExercise1();
		System.out.printf("%s: Has done the first exercise.%s\n", 
				Thread.currentThread().getName(),new Date());
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("%s: Is going to do the second exercise.%s\n", 
				Thread.currentThread().getName(),new Date());
		doExercise2();
		System.out.printf("%s: Has done the second exercise.%s\n", 
				Thread.currentThread().getName(),new Date());
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("%s: Is going to do the third exercise.%s\n", 
				Thread.currentThread().getName(),new Date());
		doExercise3();
		System.out.printf("%s: Has done the third exercise.%s\n", 
				Thread.currentThread().getName(),new Date());
		phaser.arriveAndAwaitAdvance();
	}

	private void doExercise3() {
		try {
			long duration  = (long) (Math.random() * 10);
			TimeUnit.SECONDS.wait(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doExercise2() {
		try {
			long duration  = (long) (Math.random() * 10);
			TimeUnit.SECONDS.wait(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doExercise1() {
		try {
			long duration  = (long) (Math.random() * 10);
			TimeUnit.SECONDS.wait(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
