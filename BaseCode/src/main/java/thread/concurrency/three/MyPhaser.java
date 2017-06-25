package thread.concurrency.three;

import java.util.concurrent.Phaser;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月23日 下午4:13:21
  * @version 1.0 
  */
public class MyPhaser extends Phaser{

	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		switch (phase) {
		case 0:
			return studentsArrived();
			
		case 1:
			return finishFirstExercise();

		case 2:
			return finishSecondExercise();
			
		case 3:
			return finishExam();
		default:
			return true;
		}
	}

	private boolean finishExam() {
		System.out.printf("Phaser: All the students have finished the exam.\n");
		System.out.printf("Phaser: Thank you for you time.\n");
		return false;
	}

	private boolean finishSecondExercise() {
		System.out.printf("Phaser: All the students have finished the second exercise.\n");
		System.out.printf("Phaser: It's time for the third one.\n");
		return false;
	}

	private boolean finishFirstExercise() {
		System.out.printf("Phaser: All the students have finished the first exercise.\n");
		System.out.printf("Phaser: It's time for the second one.\n");
		return false;
	}

	private boolean studentsArrived() {
		System.out.printf("Phaser: The exam are going to start. "
				+ "The students are ready.\n");
		System.out.printf("Phaser: we have %d students.\n", getRegisteredParties());
		return false;
	}
}
