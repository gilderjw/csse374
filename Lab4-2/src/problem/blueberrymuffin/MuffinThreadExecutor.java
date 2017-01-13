package problem.blueberrymuffin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;

public class MuffinThreadExecutor implements Executor {
	private static MuffinThreadExecutor instance;
	private BlockingQueue<Runnable> allJobs;
	private List<RealThread> workerThreads;

	public static MuffinThreadExecutor getInstance(){
		if(MuffinThreadExecutor.instance == null) {
			MuffinThreadExecutor.instance = new MuffinThreadExecutor();
		}
		return MuffinThreadExecutor.instance;
	}

	private MuffinThreadExecutor(){
		this.allJobs = new LinkedBlockingDeque<Runnable>();
		this.workerThreads = new ArrayList<RealThread>();

	}

	@Override
	public void execute(Runnable r){
		this.allJobs.add(r);
		try {
			this.runNextJob(null);
		} catch (InterruptedException e) {
			System.out.println("interrupted");
		}
	}

	public synchronized Runnable runNextJob(Runnable r) throws InterruptedException {
		if(this.workerThreads.size() >= 3){
			return null;
		}

		if(r != null) {
			this.workerThreads.remove(r);
		}


		RealThread tmp = new RealThread(this.allJobs.take()); //blocks

		this.workerThreads.add(tmp);
		tmp.start();

		return null;
	}
}
