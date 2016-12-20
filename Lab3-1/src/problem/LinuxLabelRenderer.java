package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class LinuxLabelRenderer extends AbstractRenderer {

	private String s;

	public LinuxLabelRenderer(IComponent c){
		this("This is a linux label", c);
	}

	public LinuxLabelRenderer(String text, IComponent c) {
		this(text, new Rectangle(100, 200, 50, 100), c);

	}

	public LinuxLabelRenderer(String text, Rectangle bound, IComponent c) {
		super(c);
		this.s = text;
		this.bound = bound;
	}

	@Override
	public void render(Graphics2D g) {
		Rectangle bound = this.cmpl.getBounds();

		// Draw the title
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.setColor(Color.black);
		g.drawString(this.s, bound.x + H_SPACE, bound.y + 16);
	}
}
