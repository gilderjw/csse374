package problem;

public class WindowsRendererFactory extends AbstractRendererFactory{

	@Override
	protected void populate() {
		//		this.put(Button.class, WindowsButtonRenderer.class);
		this.put(Label.class, WindowsLabelRenderer.class);
		this.put(TextBox.class, WindowsTextBoxRenderer.class);
		this.put(Window.class, WindowsWindowRenderer.class);
	}


}
