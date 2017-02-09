package headfirst.command.undo;

import problem.Command;

public class NoCommand implements Command {
	public void execute() { }
	public void undo() { }
}
