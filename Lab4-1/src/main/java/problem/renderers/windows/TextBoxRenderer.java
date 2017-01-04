package problem.renderers.windows;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import problem.components.IComponent;
import problem.components.TextBox;
import problem.renderers.AbstractRenderer;

public class TextBoxRenderer extends AbstractRenderer {
	private static final int H_SPACE = 5;

	public TextBoxRenderer() {
		this(null);
	}

	public TextBoxRenderer(IComponent c) {
		super(c);
	}

	@Override
	public void render(Graphics2D g) {
		TextBox c = (TextBox)this.getComponent();
		
		Rectangle bound = c.getBounds();
		
		
		// Draw the boarder after setting the thickness
		g.setColor(new Color(14,29,110));
		Rectangle2D border = new Rectangle2D.Float(bound.x, bound.y, bound.width, bound.height);
		g.setStroke(new BasicStroke(5));
		g.draw(border);
		
		// Draw the white fill
		g.setColor(Color.white);
		Rectangle2D fill = new Rectangle2D.Float(bound.x+2, bound.y+2, bound.width-2, bound.height-2);
		g.fill(fill);;
		
		// Draw the Fill
		g.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 		
		g.setColor(Color.black);
		g.drawString(c.getText(), bound.x + H_SPACE, bound.y + 16);
	}
}
