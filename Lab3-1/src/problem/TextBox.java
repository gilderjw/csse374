package problem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * This class provides the support for the text box widget.
 *
 * @author Chandan R. Rupakheti (chandan.rupakheti@rose-hulman.edu)
 */
public class TextBox extends AbstractComponent {

	private static final int H_SPACE = 5;

	private String text;

	public TextBox() {
		this(null);
	}

	public TextBox(IComponent parent, String text, Rectangle bound) {
		super(parent, bound);

		this.text = text;

		if(this.text == null) {
			this.text = "";
		}
	}

	public TextBox(String text) {
		this(text, null);
	}

	public TextBox(String text, Rectangle bound) {
		this(null, text, bound);
	}

	@Override
	public boolean addChild(IComponent c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawComponent(Graphics2D g) {
		if(Configuration.getOS().contains("windows")) {
			this.drawForMSWindow(g);
		} else if(Configuration.getOS().contains("ubuntu")) {
			this.drawForUbuntu(g);
		} else {
			throw new UnsupportedOperationException("The GUI framework  does not yet support your operating system!");
		}
	}

	private void drawForMSWindow(Graphics2D g) {
		Rectangle bound = this.getBounds();


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
		g.drawString(this.text, bound.x + H_SPACE, bound.y + 16);
	}

	private void drawForUbuntu(Graphics2D g) {
		Rectangle bound = this.getBounds();


		// Draw the boarder after setting the thickness
		g.setColor(new Color(48,0,0));
		Rectangle2D border = new Rectangle2D.Float(bound.x, bound.y, bound.width, bound.height);
		g.setStroke(new BasicStroke(5));
		g.draw(border);

		// Draw the white fill
		g.setColor(Color.white);
		Rectangle2D fill = new Rectangle2D.Float(bound.x+2, bound.y+2, bound.width-2, bound.height-2);
		g.fill(fill);;

		// Draw the Fill
		g.setFont(new Font("Arial", Font.PLAIN, 12));
		g.setColor(Color.black);
		g.drawString(this.text, bound.x + H_SPACE, bound.y + 16);
	}

	@Override
	public void fireUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IComponent> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IComponent getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRenderer getRenderer() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the text in the label.
	 */
	public String getText() {
		return this.text;
	}

	@Override
	public void setBounds(Rectangle r) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the text in the label.
	 * Calling this method results in the call to {@link #fireUpdate()},
	 * which informs the component hierarchy to re-draw itself.
	 */
	public void setText(String text) {
		this.text = text;
		this.fireUpdate();
	}
}
