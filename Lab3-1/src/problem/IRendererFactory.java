package problem;

import java.lang.reflect.InvocationTargetException;

public interface IRendererFactory {
	IRenderer createRenderer(IComponent c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
}
