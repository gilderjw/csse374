package problem;

public class LinuxRendererFactory extends AbstractRendererFactory {

	@Override
	protected void populate() {
		this.put(Button.class, LinuxButtonRenderer.class);
		this.put(Label.class, LinuxLabelRenderer.class);
		this.put(TextBox.class, LinuxTextBoxRenderer.class);
		this.put(Window.class, LinuxWindowRenderer.class);
	}


}
