package thread.concurrency.one;

public class MyThreadGroup extends ThreadGroup{

	public MyThreadGroup(String name) {
		super(name);
	}
	
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("The Thread %s has throw an Exception\n", t.getId());
		e.printStackTrace(System.out);
		System.out.printf("Terminating the rest	of the Threads\n");
		interrupt();
	}

}
