package problem.blueberrymuffin;

public class VirtualThread implements Runnable{
	private MuffinThreadExecutor MuffinThreadExecuter;
	private Runnable runnable;

	public VirtualThread(Runnable r){
		this.runnable = r;
		this.MuffinThreadExecuter = MuffinThreadExecutor.getInstance();
	}

	@Override
	public void run() {
		this.runnable.run();
		try {
			this.MuffinThreadExecuter.runNextJob(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void start() throws InterruptedException{
		this.MuffinThreadExecuter.execute(this);
	}
}
