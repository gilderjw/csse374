package problem;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button extends AbstractComponent{

	private static final int H_SPACE = 5;

	private String text;

	public Button() {
		this(null);
	}

	public Button(IComponent parent, String text, Rectangle bound) {
		super(parent, bound);

		this.text = text;

		if(this.text == null) {
			this.text = "";
		}
	}

	public Button(String text) {
		this(text, null);
	}

	public Button(String text, Rectangle bound) {
		this(null, text, bound);
	}

	@Override
	public void drawComponent(Graphics2D g) {
		this.renderer.render(g);
	}

	@Override
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
		this.fireUpdate();
	}
}

