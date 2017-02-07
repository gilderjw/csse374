package problem.robot;

import java.util.Iterator;

public class RoombaRobot implements IRobot {
	Iterator<Integer> routeItr;
	
	public RoombaRobot(Iterator<Integer> itr) {
		this.routeItr = itr;
	}

	@Override
	public int readIR() {
		if(routeItr.hasNext())
			return routeItr.next();
		return -1;
	}

	@Override
	public void steady(int sensor) {
		System.out.format("Steady state correction [Sensor - %d]%n", sensor);
	}

	@Override
	public void correctLeft(int sensor) {
		System.out.format("Left correction [Sensor - %d]%n", sensor);
	}

	@Override
	public void correctRight(int sensor) {
		System.out.format("Right correction [Sensor - %d]%n", sensor);
	}
}
