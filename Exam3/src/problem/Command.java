package problem;

import java.util.Iterator;

public interface Command extends Iterable<Command> {
	// Preserve these method signatures.
	public void execute();
	public void undo();
	// Add methods with default implementations below as needed.
	
	default void addChildCommand(Command c) {
		throw new UnsupportedOperationException();
	}
	
	default void removeChildCommand(Command c) {
		throw new UnsupportedOperationException();
	}
	
	default Iterator<Command> iterator(){
		return new NullIterator();
	}
}
