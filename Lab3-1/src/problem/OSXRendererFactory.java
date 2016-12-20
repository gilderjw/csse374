package problem;

public class OSXRendererFactory extends AbstractRendererFactory {

	@Override
	protected void populate() {
		this.put(Label.class, OSXLabelRenderer.class);
		this.put(Window.class, OSXWindowRenderer.class);
		this.put(Button.class, WindowsButtonRenderer.class);
		this.put(TextBox.class, WindowsTextBoxRenderer.class);
	}

}
