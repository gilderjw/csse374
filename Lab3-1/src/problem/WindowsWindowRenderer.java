package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowsWindowRenderer extends AbstractRenderer {

	protected String title;
	protected JFrame frame;
	protected JPanel panel;

	public WindowsWindowRenderer(IComponent c){
		this(c.getText(), new Rectangle(200, 400, 400, 600), c);

	}

	public WindowsWindowRenderer(String title, Rectangle bound, IComponent c) {
		super(c);
		this.bound = c.getBounds();

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
				WindowsWindowRenderer.this.render((Graphics2D) g);
			}
		};
		this.frame.setContentPane(this.panel);
	}

	@Override
	public IComponent getComponenet() {
		// TODO Auto-generated method stub
		return null;
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
