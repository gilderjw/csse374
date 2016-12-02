package problem;

import java.io.IOException;
import java.nio.file.Path;

public class ExecutableFileRunnerLinux extends ProcessRunner{
	@Override
	void execute(Path p) {
		ProcessBuilder processBuilder = new ProcessBuilder("wine", p.toAbsolutePath().toString());
		try {
			Process process = processBuilder.start();
			this.processes.add(process);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
