package problem.components;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.rosehulman.csse374.wired.annotations.Autowired;
import problem.renderers.IRenderer;
import problem.renderers.RendererAllOSFactory;

public abstract class AbstractComponent implements IComponent {
	private IComponent parent;
	private List<IComponent> components;
	private Rectangle bound;
	
	@Autowired
	private RendererAllOSFactory rendererFactory;
	private IRenderer renderer;

	public AbstractComponent() {
		this(null, null);
	}

	public AbstractComponent(Rectangle bound) {
		this(null, bound);
	}
	
	public AbstractComponent(IComponent parent, Rectangle bound) {
		this.parent = parent;
		if(parent != null)
			this.parent.addChild(this);
		
		this.components = Collections.synchronizedList(new ArrayList<IComponent>());
		this.bound = bound;
		
		if(this.bound == null)
			this.bound = IComponent.DEFAULT_BOUNDS;
	}

	@Override
	public final Rectangle getBounds() {
		return this.bound;
	}

	@Override
	public final void setBounds(Rectangle bound) {
		this.bound = bound;
		this.fireUpdate();
	}
	
	@Override
	public final IRenderer getRenderer() {
		if(this.renderer != null)
			return this.renderer;

		try {
			this.renderer = rendererFactory.create(IRenderer.class, this.getClass().getSimpleName());
			this.renderer.setComponent(this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return this.renderer;
	}

	@Override
	public final IComponent getParent() {
		return this.parent;
	}

	@Override
	public final List<IComponent> getChildren() {
		return Collections.unmodifiableList(this.components);
	}
	
	@Override
	public final boolean addChild(IComponent c) {
		if(this.components.contains(c))
			return false;
		
		boolean changed = this.components.add(c);
		if(changed) {
			((AbstractComponent)c).parent = this;
			this.fireUpdate();
		}

		return changed;
	}

	@Override
	public final void draw(Graphics2D g) {
		this.drawComponent(g);
		synchronized(this.components) {
			for(IComponent c: this.components) {
				c.draw(g);
			}
		}
	}
	
	@Override
	public void fireUpdate() {
		if(parent != null) {
			parent.fireUpdate();
		}
	}
	
	@Override
	public void drawComponent(Graphics2D g) {
		this.getRenderer().render(g);
	}
}
