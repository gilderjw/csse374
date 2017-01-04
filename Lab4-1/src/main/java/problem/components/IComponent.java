package problem.components;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

import problem.renderers.IRenderer;

public interface IComponent {
	public static final Rectangle DEFAULT_BOUNDS = new Rectangle(0,0,50,50);
	
	public IComponent getParent();
	boolean addChild(IComponent c);
	public List<IComponent> getChildren();
	
	public Rectangle getBounds();
	public void setBounds(Rectangle r);
	
	public void draw(Graphics2D g);
	public void drawComponent(Graphics2D g);
	
	public void fireUpdate();
	
	public IRenderer getRenderer();
}

