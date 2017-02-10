package problem.actions;

public class CorrectLeftAction implements Action {

	@Override
	public void execute(int sensor) {
		System.out.format("Left correction [Sensor - %d]%n", sensor);
	}
}
