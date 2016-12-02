package problem;

import java.nio.file.Path;

public class DirectoryEvent {
	private String type;
	private Path file;
	private IDirectoryMonitorService service;

	public DirectoryEvent(String type, Path file, IDirectoryMonitorService service){
		this.type = type;
		this.service = service;
		this.file = file;
	}

	String getEventType(){
		return this.type;

	}

	Path getFile(){
		return this.file;

	}

	IDirectoryMonitorService getSource(){
		return this.service;

	}
}
