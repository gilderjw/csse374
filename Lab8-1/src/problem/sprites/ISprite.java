package problem.sprites;

import java.awt.Dimension;
import java.awt.Shape;
import java.util.List;

// TODO: Add support for complex (composite) sprites
public interface ISprite extends Iterable<ISprite> {
	public void move(Dimension space);
	public Shape getShape();
}
