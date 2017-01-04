package problem.renderers;

import java.awt.Graphics2D;

import problem.components.IComponent;

public abstract class AbstractRenderer implements IRenderer {
	private IComponent component;

	public AbstractRenderer(IComponent c) {
		this.component = c;
	}
	
	@Override
	public final IComponent getComponent() {
		return this.component;
	}
	
	public final void setComponent(IComponent c) {
		this.component = c;
	}

	@Override
	public abstract void render(Graphics2D g);
}
