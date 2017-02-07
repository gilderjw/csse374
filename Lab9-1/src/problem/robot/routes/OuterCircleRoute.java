package problem.robot.routes;

import java.util.Iterator;
import java.util.Random;

import problem.robot.EnvtParam;

public class OuterCircleRoute implements IRoute {
	int entries;
	EnvtParam param;
	
	public OuterCircleRoute(int entries, EnvtParam param) {
		this.entries = entries;
		this.param = param;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new OuterCircleIterator();
	}
	
	private class OuterCircleIterator implements Iterator<Integer> {
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
			
			int low = param.getLow();
			int delta = param.getDelta();

			// Always far but different values
			return delta - low - (10 + random.nextInt(delta-10));
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
