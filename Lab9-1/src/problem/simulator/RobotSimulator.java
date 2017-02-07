package problem.simulator;

import problem.robot.EnvtParam;
import problem.robot.IRobot;
import problem.robot.RoombaRobot;
import problem.robot.routes.IRoute;
import problem.robot.routes.InnerCircleRoute;
import problem.robot.routes.OuterCircleRoute;
import problem.robot.routes.SawToothRoute;
import problem.robot.routes.StraightLineRoute;

public class RobotSimulator {

	public static void main(String[] args) {
		EnvtParam param = new EnvtParam(0, 4000, 1000);

		IRoute route = new StraightLineRoute(5, param);
		run(param, route, "Running Roomba in the straight line ...");
	
		route = new InnerCircleRoute(5, param);
		run(param, route, "Running Roomba inside a circular wall ...");
		
		route = new OuterCircleRoute(5, param);
		run(param, route, "Running Roomba outside a circular wall ...");

		// SawToothRoute only has 3 points
		route = new SawToothRoute(param);
		run(param, route, "Running Roomba in a saw-tooth-like route ...");
	}
	
	// NOTE: Even though this method is in the entry class, it is still part of core logic
	public static void run(EnvtParam param, IRoute route, String heading) {
		System.out.println("--------------------------------------------");
		System.out.println(heading);
		System.out.println("--------------------------------------------");
		
		IRobot robot = new RoombaRobot(route.iterator());		

		// TODO: Change the logic below
		
		int low = param.getLow();
		int high = param.getHigh();
		int delta = param.getDelta();

		// Assume the wall IR sensor is on the right side of the robot
		int sensor = robot.readIR();
		while(sensor != -1) {
			// Too near, correct/speed-up right wheel
			if(sensor > high - delta)
				robot.correctRight(sensor);

			// Too far, correct/speed-up left wheel
			if(sensor < delta - low)
				robot.correctLeft(sensor);
			
			// About right, lightly adjust left/right wheels
			if(delta - low <= sensor && sensor <= high - delta)
				robot.steady(sensor);

			// Read next IR value
			sensor = robot.readIR();
		}
	}
}
