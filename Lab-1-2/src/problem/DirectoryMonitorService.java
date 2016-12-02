package problem;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

public class DirectoryMonitorService extends Thread implements IDirectoryMonitorService {
	private ArrayList<IDirectoryListener> listeners;
	private Path dir;
	private boolean running;
	private WatchService watcher;

	public DirectoryMonitorService(Path dir) throws IOException {
		this.listeners = new ArrayList<IDirectoryListener>();
		this.dir = dir;
		this.watcher = FileSystems.getDefault().newWatchService();
		dir.register(this.watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
	}

	@Override
	public void addListener(IDirectoryListener l) {
		this.listeners.add(l);
	}

	@Override
	public Path getDirectory() {
		return this.dir;
	}

	@Override
	public boolean isRunning() {
		return this.running;
	}

	void notifyListener(DirectoryEvent e){
		for(IDirectoryListener l : this.listeners) {
			l.directoryChanged(e);
		}
	}

	@Override
	public void removeListener(IDirectoryListener l) {
		this.listeners.remove(l);
	}

	/**
	 * Process all events for keys queued to the watcher
	 */
	@Override
	public void run() {
		this.running = true;
		while(this.running) {

			// Wait for key to be signalled
			WatchKey key;
			try {
				key = this.watcher.take();
			} catch (InterruptedException x) {

				return;
			}

			// Context for directory entry event is the file name of entry
			List<WatchEvent<?>> events = key.pollEvents();
			if(!events.isEmpty()) {
				@SuppressWarnings("unchecked")
				WatchEvent<Path> event = (WatchEvent<Path>)events.get(0);
				Path name = event.context();
				Path child = this.dir.resolve(name);

				DirectoryEvent dEvent = new DirectoryEvent(event.kind().name(), child, this);
				// Call the handler method
				if(event.kind() == ENTRY_CREATE) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.notifyListener(dEvent);
				}
			}

			// Reset key and remove from set if directory no longer accessible
			if (!key.reset()) {
				break;
			}

		}

		// We gracefully stopped the service now, let's delete the temp file
		this.stopGracefully();
	}

	@Override
	public void stopGracefully() {
		this.running = false;
		this.interrupt();
		File file = new File(this.dir.toFile() + "/.temp");
		file.delete();

		for(IDirectoryListener l: this.listeners) {
			l.directoryChanged(null); //null means kill yourself
		}
	}
}




















