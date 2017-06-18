import org.junit.Test;

public class ThreadTest {
	
	@Test
	public void test(){
		thread.Thread ds1 = new thread.Thread("阿三");
		thread.Thread ds2 = new thread.Thread("李四");

        Thread t1 = new Thread(ds1);
        Thread t2 = new Thread(ds2);

        t1.start(); 
        t2.start(); 
	}

}
