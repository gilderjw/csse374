package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class WindowsLabelRenderer extends AbstractRenderer {

	private String s;

	public WindowsLabelRenderer(IComponent c){
		this("This is a windows label", c);
	}

	public WindowsLabelRenderer(String text, IComponent c) {
		this(text, new Rectangle(100, 200, 50, 100), c);

	}

	public WindowsLabelRenderer(String text, Rectangle bound, IComponent c) {
		super(c);
		this.s = text;
		this.bound = bound;
	}

	@Override
	public IComponent getComponenet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void render(Graphics2D g) {
		Rectangle bound = this.cmpl.getBounds();

		// Draw the title
		g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		g.setColor(Color.black);
		g.drawString(this.s, bound.x + H_SPACE, bound.y + 16);

	}

}
