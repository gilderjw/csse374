package problem;

import headfirst.command.undo.NoCommand;

//
// This is the invoker
//
public class RemoteControl {
	Command[] onCommands;
	Command[] offCommands;
	Command undoCommand;
 
	public RemoteControl() {
		onCommands = new Command[10];
		offCommands = new Command[10];
 
		Command noCommand = new NoCommand();
		for(int i=0;i<10;i++) {
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}
		undoCommand = noCommand;
	}
  
	public void setCommand(int slot, Command onCommand, Command offCommand) {
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}
 
	public void onButtonWasPushed(int slot) {
		// Do not change this code.
		onCommands[slot].execute();
		undoCommand = onCommands[slot];
	}
 
	public void offButtonWasPushed(int slot) {
		// Do not change this code.
		offCommands[slot].execute();
		undoCommand = offCommands[slot];
	}
	
	public void addToMacro(int slot_to_add, int macro_slot){
		// FIXME: implement this code.
	}

	public void removeFromMacro(int slot_to_remove, int macro_slot){
		// FIXME: implement this code using internal iteration for a B.
		// FIXME: implement this code using external iteration for an A.
	}

	public void undoButtonWasPushed() {
		// Do not change this code.
		undoCommand.undo();
	}
  
	public String toString() {
		StringBuffer stringBuff = new StringBuffer();
		stringBuff.append("\n------ Remote Control -------\n");
		for (int i = 0; i < onCommands.length; i++) {
			stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getName()
				+ "    " + offCommands[i].getClass().getName() + "\n");
		}
		stringBuff.append("[undo] " + undoCommand.getClass().getName() + "\n");
		return stringBuff.toString();
	}
}
