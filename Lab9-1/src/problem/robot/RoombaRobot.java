package problem.robot;

import java.util.Iterator;

import problem.states.State;

public class RoombaRobot implements IRobot {
	Iterator<Integer> routeItr;
	State s;

	public RoombaRobot(Iterator<Integer> itr) {
		this.routeItr = itr;
	}

	@Override
	public int readIR() {
		if(this.routeItr.hasNext()) {
			int tmp = this.routeItr.next();
			this.s.execute(tmp);
			this.s = this.s.getNextState(tmp);
			return tmp;
		}
		return -1;
	}

	//	@Override
	//	public void steady(int sensor) {
	//		System.out.format("Steady state correction [Sensor - %d]%n", sensor);
	//	}
	//
	//	@Override
	//	public void correctLeft(int sensor) {
	//		System.out.format("Left correction [Sensor - %d]%n", sensor);
	//	}
	//
	//	@Override
	//	public void correctRight(int sensor) {
	//		System.out.format("Right correction [Sensor - %d]%n", sensor);
	//	}



	@Override
	public void setState(State s) {
		this.s = s;
	}
}
