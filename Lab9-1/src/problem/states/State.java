package problem.states;

import problem.actions.Action;
import problem.robot.EnvtParam;

public abstract class State {
	protected Action a;
	protected EnvtParam param;

	public abstract State getNextState(int sensor);

	public State(EnvtParam param){
		this.param = param;
	}

	public void execute(int sensor){
		this.a.execute(sensor);
	}

	public void setAction(Action a){
		this.a = a;
	}
}
