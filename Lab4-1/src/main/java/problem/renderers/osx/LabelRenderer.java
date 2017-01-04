package problem.renderers.osx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import problem.components.IComponent;
import problem.components.Label;
import problem.renderers.AbstractRenderer;

public class LabelRenderer extends AbstractRenderer {
	private static final int H_SPACE = 3;

	public LabelRenderer() {
		this(null);
	}

	public LabelRenderer(IComponent c) {
		super(c);
	}

	@Override
	public void render(Graphics2D g) {
		Label c = (Label)this.getComponent();

		Rectangle bound = c.getBounds();
		
		// Draw the title
		g.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 		
		g.setColor(Color.black);
		g.drawString(c.getText(), bound.x + H_SPACE, bound.y + 16);
	}
}
