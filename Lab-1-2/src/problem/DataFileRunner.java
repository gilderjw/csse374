package problem;

import java.io.IOException;
import java.nio.file.Path;

public class DataFileRunner extends ProcessRunner {

	public DataFileRunner(){
		this.command = "Notepad";
	}


	@Override
	void execute(Path p) {
		ProcessBuilder processBuilder = new ProcessBuilder(this.command, p.toAbsolutePath().toString());
		try {
			Process process = processBuilder.start();
			this.processes.add(process);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
