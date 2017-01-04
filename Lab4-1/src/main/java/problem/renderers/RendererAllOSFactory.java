package problem.renderers;

import edu.rosehulman.csse374.wired.api.IFactory;
import edu.rosehulman.csse374.wired.core.AbstractFactory;
import problem.renderers.linux.LinuxRendererFactory;
import problem.renderers.osx.OSXRendererFactory;
import problem.renderers.windows.WindowRendererFactory;

public class RendererAllOSFactory extends AbstractFactory {
	public RendererAllOSFactory() {
		this.map("linux", LinuxRendererFactory.class);
		this.map("windows", WindowRendererFactory.class);
		this.map("mac", OSXRendererFactory.class);
	}

	@Override
	protected <T> T createSpecial(Class<T> clazz, String selector) throws Exception {
		// Check whether we are asked to create a IRenderer type or its subtype
		if(!IRenderer.class.isAssignableFrom(clazz))
			return null;
		
		Configuration config = Configuration.getInstance();
		// Let's get the correct factory for current OS, which we mapped in the constructor
		IFactory osFactory = this.create(IFactory.class, config.getOS());
		return osFactory.create(clazz, selector);
	}
}
