package problem.robot;

public interface IRobot {
	public int readIR();
	public void steady(int sensor);
	public void correctLeft(int sensor);
	public void correctRight(int sensor);
}
