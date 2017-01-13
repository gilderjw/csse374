package problem.blueberrymuffin;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;

public class MuffinThreadExecutor implements Executor {
	private static MuffinThreadExecutor instance;
	private Queue<Runnable> allJobs;
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
		this.getNextJob();
	}

	public synchronized Runnable getNextJob() {
		if (!this.workerThreads.isEmpty()){

		}
		return null;
	}
}
