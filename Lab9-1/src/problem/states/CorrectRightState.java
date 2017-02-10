package problem.states;

import problem.actions.CorrectRightAction;
import problem.robot.EnvtParam;

public class CorrectRightState extends State {

	public CorrectRightState(EnvtParam param) {
		super(param);
		this.a = new CorrectRightAction();
	}

	@Override
	public void execute(int sensor) {
		System.out.format("Right correction [Sensor - %d]%n", sensor);
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
