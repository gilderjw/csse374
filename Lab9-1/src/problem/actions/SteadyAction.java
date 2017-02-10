package problem.actions;

public class SteadyAction implements Action {

	@Override
	public void execute(int sensor) {
		System.out.format("Steady state correction [Sensor - %d]%n", sensor);
	}
}
