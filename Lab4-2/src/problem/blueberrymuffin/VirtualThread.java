package problem.blueberrymuffin;

import java.util.concurrent.Executor;

public class VirtualThread implements Runnable{
	private Executor MuffinThreadExecuter;
	private Runnable runnable;

	public VirtualThread(MuffinThreadExecutor me, Runnable r){
		this.runnable = r;
		this.MuffinThreadExecuter = me;
	}

	@Override
	public void run() {
		this.MuffinThreadExecuter.execute(this);
	}

	public void start(){
		this.run();
	}
}
