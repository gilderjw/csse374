package problem.renderers.osx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import problem.components.IComponent;
import problem.components.Window;
import problem.renderers.AbstractRenderer;

public class WindowRenderer extends AbstractRenderer {
	public static final int V_SPACE = 3;
	public static final int H_SPACE = 3;
	public static final int TITLE_HEIGHT = 25;

	public WindowRenderer() {
		this(null);
	}

	public WindowRenderer(IComponent c) {
		super(c);
	}

	@Override
	public void render(Graphics2D g) {
		Window c = (Window)this.getComponent();
		
		// Draw the border
		g.setColor(Color.gray);
		g.draw3DRect(1, 1, c.getBounds().width - H_SPACE, (int)c.getBounds().height - V_SPACE, true);

		// Draw the title bar
		g.setColor(new Color(49, 68, 176));
		g.fill3DRect(1, 1, c.getBounds().width - H_SPACE , TITLE_HEIGHT - 5, true);
		
		// Draw the title
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 		
		g.setColor(Color.white);
		g.drawString(c.getTitle(), 5, 17);
	}
}
