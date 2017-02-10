package problem.robot;

import problem.states.State;

public interface IRobot {
	public int readIR();
	public void setState(State s);
	//	public void steady(int sensor);
	//	public void correctLeft(int sensor);
	//	public void correctRight(int sensor);
}
