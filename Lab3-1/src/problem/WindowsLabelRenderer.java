package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class WindowsLabelRenderer extends AbstractRenderer {

	private String s;

	public WindowsLabelRenderer(){
		this("This is a windows label");
	}

	public WindowsLabelRenderer(String text) {
		this(text, new Rectangle(100, 200, 50, 100));

	}

	public WindowsLabelRenderer(String text, Rectangle bound) {
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
		Rectangle bound = this.getBounds();

		// Draw the title
		g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		g.setColor(Color.black);
		g.drawString(this.s, bound.x + H_SPACE, bound.y + 16);

	}

}
