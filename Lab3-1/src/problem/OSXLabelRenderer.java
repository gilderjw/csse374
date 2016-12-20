package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class OSXLabelRenderer extends AbstractRenderer {
	private String s;

	public OSXLabelRenderer(IComponent c){
		this("This is an OSX label", c);
	}

	public OSXLabelRenderer(String text, IComponent c) {
		this(text, new Rectangle(100, 200, 50, 100), c);

	}

	public OSXLabelRenderer(String text, Rectangle bound, IComponent c) {
		super(c);
		this.s = text;
		this.bound = bound;
	}

	@Override
	public void render(Graphics2D g) {
		Rectangle bound = this.cmpl.getBounds();

		// Draw the title
		g.setFont(new Font("Helvetica", Font.PLAIN, 12));
		g.setColor(Color.black);
		g.drawString(this.s, bound.x + H_SPACE, bound.y + 16);

	}
}
