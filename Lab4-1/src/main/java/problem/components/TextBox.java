package problem.components;

import java.awt.Rectangle;

public class TextBox extends AbstractComponent {
	private String text;
	
	public TextBox() {
		this(null);
	}

	public TextBox(String text) {
		this(text, null);
	}

	public TextBox(String text, Rectangle bound) {
		this(null, text, bound);
	}

	public TextBox(IComponent parent, String text, Rectangle bound) {
		super(parent, bound);
		
		this.text = text;
		
		if(this.text == null)
			this.text = "";
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
		this.fireUpdate();
	}
}
