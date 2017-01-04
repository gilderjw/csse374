package problem.components;

import java.awt.Rectangle;

public class Label extends AbstractComponent {
	private String text;
	
	public Label() {
		this("", null);
	}
	
	public Label(String text) {
		this(text, null);
	}

	public Label(String text, Rectangle bound) {
		this(null, text, bound);
	}

	public Label(IComponent parent, String text, Rectangle bound) {
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
