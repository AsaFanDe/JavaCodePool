package thread.concurrency.four;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/** * @author  作者 : 范德胜
  * @date 创建时间：2017年6月24日 下午12:44:34
  * @version 1.0 
  */
public class UserValidator {
	
	private String name;
	
	public UserValidator(String name) {
		this.name = name;
	}

	public boolean validate(String name, String password){
		Random random = new Random();
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("Validate %s：Validate a user during %d secind\n", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			return false;
		}
		return random.nextBoolean();
	}
	
	public String getName(){
		return name;
	}
}
