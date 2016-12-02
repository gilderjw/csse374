package problem;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class ProcessRunner {
	protected ArrayList<Process> processes = new ArrayList<Process>();
	protected String command;

	abstract void execute(Path p);

	String getCommand() {
		return this.command;
	}

	List<Process> getProcesses() {
		return this.processes;
	}
}
