package problem.robot.routes;

import java.util.Iterator;

public interface IRoute extends Iterable<Integer> {
	public Iterator<Integer> iterator();
}
