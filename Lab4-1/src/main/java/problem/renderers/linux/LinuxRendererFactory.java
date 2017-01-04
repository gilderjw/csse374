package problem.renderers.linux;

import edu.rosehulman.csse374.wired.core.AbstractFactory;
import problem.components.Button;
import problem.components.Label;
import problem.components.TextBox;
import problem.components.Window;

// Note that we are intentionally not using @Factory because
// the super type of all of these renderer is IRenderer and
// autowiring without a selector will always pick up the first
// registered renderer, which is the WindowRenderer even when
// we want to render, for example, a Label.
public class LinuxRendererFactory extends AbstractFactory {
	public LinuxRendererFactory() {
		this.map(Window.class.getSimpleName(), WindowRenderer.class);
		this.map(Label.class.getSimpleName(), LabelRenderer.class);
		this.map(TextBox.class.getSimpleName(), TextBoxRenderer.class);
		this.map(Button.class.getSimpleName(), ButtonRenderer.class);
	}
}
