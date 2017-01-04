package problem.components;

import java.awt.Rectangle;

public class Button extends AbstractComponent {
	private String text;
	
	public Button() {
		this(null);
	}

	public Button(String text) {
		this(text, null);
	}

	public Button(String text, Rectangle bound) {
		this(null, text, bound);
	}

	public Button(IComponent parent, String text, Rectangle bound) {
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
