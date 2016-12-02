package problem;

public class DirectoryChangeLogger implements IDirectoryListener {
	private int logged = 0;

	@Override
	public void directoryChanged(DirectoryEvent e) {
		this.logged++;
		System.out.println(this.logged + ": " + e.getEventType().toString() + " event at " + e.getFile().toString());
	}

	public int getLogged(){
		return this.logged;
	}
}
