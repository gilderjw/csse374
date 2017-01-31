package problem.sprites;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//!!!!! NOTE: Do not change me !!!!!!!
public class SpriteFactory {
	private static SpriteFactory instance = new SpriteFactory();
	
	public static SpriteFactory getInstance() {
		return instance;
	}
	
	private List<Class<? extends ISprite>> sprites;
	private Random random; 
	
	private SpriteFactory() {
		random = new Random();
		sprites = new ArrayList<>();
	}

	@SafeVarargs
	public final void load(Class<? extends ISprite>... clazz) {
		sprites.addAll(Arrays.asList(clazz));
	}
	
	public double getDX() {
		return 5;
	}
	
	public double getDY() {
		return 5;
	}
	
	public double getWidth() {
		return 50;
	}

	public double getHeight() {
		return 50;
	}
	
	public ISprite createRandomSprite(Dimension space) throws Exception {
		int index = random.nextInt(sprites.size());
		Class<? extends ISprite> clazz = sprites.get(index);
		Constructor<? extends ISprite> ctor = clazz.getDeclaredConstructor(double.class, double.class, double.class, double.class);
		
		Point2D aPoint = computeRandomLocation(space);
		return ctor.newInstance(aPoint.getX(), aPoint.getY(), getWidth(), getHeight());
	}

	private Point2D computeRandomLocation(Dimension space) {
		Random r = new Random();
		double x = r.nextInt(space.width);
		double y = r.nextInt(space.height);
		
		return new Point2D.Double(x, y);
	}	
}
