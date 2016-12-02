package problem;

import java.util.Collection;

public interface IApplicationLauncher extends IDirectoryListener {
	void addRunner(String extension, ProcessRunner runner);
	Collection<ProcessRunner> getRunners();
	void shutDown();
}
