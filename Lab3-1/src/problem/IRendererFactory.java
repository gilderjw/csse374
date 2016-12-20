package problem;

public interface IRendererFactory {
	IRenderer createRenderer(IComponent c) throws InstantiationException, IllegalAccessException;
}
