package problem;

import java.awt.Rectangle;

public abstract class AbstractRenderer implements IRenderer {

	public static final int V_SPACE = 3;
	public static final int H_SPACE = 3;
	public static final int TITLE_HEIGHT = 25;
	protected Rectangle bound;

	IComponent cmpl;

	public AbstractRenderer(IComponent cmpl) {
		this.cmpl = cmpl;
	}

	protected Rectangle getBounds(){
		return this.bound;
	}
	public IComponent getComponent(){
		return this.cmpl;
	}

}
