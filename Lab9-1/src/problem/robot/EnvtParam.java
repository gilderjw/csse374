package problem.robot;

public class EnvtParam {
	int low;
	int high;
	int delta;
	
	public EnvtParam(int low, int high, int delta) {
		this.low = low;
		this.high = high;
		this.delta = delta;
	}

	public int getLow() {
		return low;
	}

	public int getHigh() {
		return high;
	}

	public int getDelta() {
		return delta;
	}
}
