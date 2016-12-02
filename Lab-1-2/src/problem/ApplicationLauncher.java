package problem;

import java.util.Collection;
import java.util.HashMap;

public class ApplicationLauncher implements IApplicationLauncher {
	HashMap<String, ProcessRunner> processes;

	public ApplicationLauncher(){
		this.processes = new HashMap<String, ProcessRunner>();
	}

	@Override
	public void addRunner(String extension, ProcessRunner runner){
		this.processes.put(extension, runner);
	}

	@Override
	public void directoryChanged(DirectoryEvent e){
		if (e == null){
			this.shutDown();
			return;
		}
		String fileName = e.getFile().getFileName().toString();
		String extension = fileName.substring(fileName.lastIndexOf('.'));

		if(this.processes.containsKey(extension)){
			this.processes.get(extension).execute(e.getFile().toAbsolutePath());
		}
	}

	@Override
	public Collection<ProcessRunner> getRunners(){
		return this.processes.values();
	}

	@Override
	public void shutDown() {
		for(ProcessRunner process: this.processes.values()){
			for(Process p: process.getProcesses()) {
				p.destroy();
			}
		}
	}

}
