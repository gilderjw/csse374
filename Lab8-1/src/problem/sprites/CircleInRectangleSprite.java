package problem.sprites;


import java.awt.Dimension;
import java.util.Iterator;

public class CircleInRectangleSprite extends AbstractSprite {

	public CircleInRectangleSprite(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.children.add(new CircleSprite(x, y, width, height));
		this.children.add(new RectangleSprite(x, y, width, height));
		
	}

	@Override
	public Iterator<ISprite> iterator() {
		return this.children.iterator();
	}

	@Override
	public void move(Dimension space) {
	}

}
