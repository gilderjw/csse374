package problem.robot.routes;

import java.util.Iterator;
import java.util.Random;

import problem.robot.EnvtParam;

public class InnerCircleRoute implements IRoute {
	int entries;
	EnvtParam param;
	
	public InnerCircleRoute(int entries, EnvtParam param) {
		this.entries = entries;
		this.param = param;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new InnerCircleIterator();
	}
	
	private class InnerCircleIterator implements Iterator<Integer> {
		int count = 0;
		Random random = new Random();
		
		@Override
		public boolean hasNext() {
			return count < entries;
		}

		@Override
		public Integer next() {
			if(count++ >= entries) {
				throw new IndexOutOfBoundsException();
			}
			
			int high = param.getHigh();
			int delta = param.getDelta();

			// Always near but different values
			return high - delta + 10 + random.nextInt(delta - 10);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
