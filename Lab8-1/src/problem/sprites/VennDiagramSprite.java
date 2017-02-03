package problem.sprites;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

public class VennDiagramSprite extends AbstractSprite {

	public VennDiagramSprite(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.children.add(new CircleSprite(x, y, width, height));
		this.children.add(new CircleSprite(x+width/2, y, width, height));
		
	}

	@Override
	public Iterator<ISprite> iterator() {
		return this.children.iterator();
	}

	@Override
	public void move(Dimension space) {
	}

}
