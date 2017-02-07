package problem.robot.routes;

import java.util.Iterator;
import problem.robot.EnvtParam;

public class SawToothRoute implements IRoute {
	EnvtParam param;
	
	public SawToothRoute(EnvtParam param) {
		this.param = param;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new SawToothIterator();
	}
	
	private class SawToothIterator implements Iterator<Integer> {
		int count = 0;
		
		@Override
		public boolean hasNext() {
			return count < 3;
		}

		@Override
		public Integer next() {
			int low = param.getLow();
			int high = param.getHigh();
			int delta = param.getDelta();
			
			switch(count++) {
			case 0:
				return delta - low - 1;
			case 1:
				return high - delta + 1;
			case 2:
				return delta - low;
			default:
				throw new IndexOutOfBoundsException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
