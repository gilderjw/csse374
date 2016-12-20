package problem;

import java.awt.Graphics2D;

public interface IRenderer {
	public IComponent getComponent();
	public void render(Graphics2D g);
}
