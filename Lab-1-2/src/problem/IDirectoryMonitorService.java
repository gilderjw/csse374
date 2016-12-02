package problem;

import java.nio.file.Path;

public interface IDirectoryMonitorService  {
	void addListener(IDirectoryListener l);
	Path getDirectory();
	boolean isRunning();
	void removeListener(IDirectoryListener l);
	void start();
	void stopGracefully();
}
