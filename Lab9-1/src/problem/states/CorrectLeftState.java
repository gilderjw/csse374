package problem.states;

import problem.actions.CorrectLeftAction;
import problem.robot.EnvtParam;

public class CorrectLeftState extends State{

	public CorrectLeftState(EnvtParam param) {
		super(param);
		this.a = new CorrectLeftAction();
	}

	@Override
	public State getNextState(int sensor) {
		int low = this.param.getLow();
		int high = this.param.getHigh();
		int delta = this.param.getDelta();
		// Too near, correct/speed-up right wheel
		if(sensor > (high - delta)) {
			return new CorrectRightState(this.param);
		}

		// Too far, correct/speed-up left wheel
		if(sensor < (delta - low)) {
			return new CorrectLeftState(this.param);
		}

		// About right, lightly adjust left/right wheels
		return new SteadyState(this.param);
	}

}
