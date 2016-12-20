package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LinuxWindowRenderer extends AbstractRenderer{

	public static final int V_SPACE = 3;
	public static final int H_SPACE = 3;
	public static final int TITLE_HEIGHT = 25;
	private Rectangle bound;
	private String title;
	private JFrame frame;
	private JPanel panel;

	public LinuxWindowRenderer(String title, Rectangle bound) {
		this.bound = bound;

		if(title == null) {
			this.title = "";
		} else {
			this.title = title;
		}

		this.frame = new JFrame(this.title);
		this.frame.setUndecorated(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				LinuxWindowRenderer.this.render((Graphics2D) g);
			}
		};
		this.frame.setContentPane(this.panel);
	}

	private Rectangle getBounds(){
		return this.bound;
	}

	@Override
	public void render(Graphics2D g) {
		// Draw the border
		g.setColor(Color.gray);
		g.draw3DRect(1, 1, this.getBounds().width - H_SPACE, this.getBounds().height - V_SPACE, true);

		// Draw the title bar
		g.setColor(new Color(49, 68, 176));
		g.fill3DRect(1, 1, this.getBounds().width - H_SPACE , TITLE_HEIGHT - 5, true);

		// Draw the title
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g.setColor(Color.white);
		g.drawString(this.title, 5, 17);
	}

}