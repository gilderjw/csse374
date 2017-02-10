package problem.actions;

public class CorrectRightAction implements Action{

	@Override
	public void execute(int sensor) {
		System.out.format("Right correction [Sensor - %d]%n", sensor);
	}
}
