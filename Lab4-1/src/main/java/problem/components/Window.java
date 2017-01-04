package problem.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends AbstractComponent {
	public static final int V_SPACE = 3;
	public static final int H_SPACE = 3;
	public static final int TITLE_HEIGHT = 25;
	
	private String title;
	private JFrame frame;
	private JPanel panel;

	public Window() {
		this(null);
	}

	public Window(String title) {
		this(title, null);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}

	@SuppressWarnings("serial")
	public Window(String title, Rectangle bound) {
		super(null, bound);

		if(title == null)
			this.title = "";
		else
			this.title = title;

		this.frame = new JFrame(this.title);
		this.frame.setUndecorated(true);	
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				draw((Graphics2D) g);
			}
		};
		this.frame.setContentPane(this.panel);
	}

	@Override
	public final void fireUpdate() {
		this.panel.repaint();
	}
	
	public void show() {
		this.frame.setBounds(this.getBounds());
		this.frame.setVisible(true);
	}
}
