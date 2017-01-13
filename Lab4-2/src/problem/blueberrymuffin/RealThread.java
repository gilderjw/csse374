package problem.blueberrymuffin;

/**
 * Simulates threading on the Blueberry device.
 * 
 * The Blueberry can only handle 4 threads.
 * Your computer can handle more than 4, so this class simulates that constraint for testing purposes.
 *
 */
public class RealThread {
	private Thread underlying;
	public RealThread(Runnable r) {
		underlying = new Thread(r); // using the JVM thread only to simulate the real threading code on the device.
	}

	public synchronized void start() {
		underlying.start();
		if(getTotalThreads() > 4){
			try{
				throw new IllegalStateException("Maximum allowed RealThread count exceeded.");
			}finally{
				System.err.println("CRITICAL: Maximum allowed RealThread count exceeded. BLUEBERRY SIMULATION TERMINATED.");
				System.exit(1);
			}
		}
	}

	public static int getTotalThreads(){
		return Thread.currentThread().getThreadGroup().activeCount();
	}
}
