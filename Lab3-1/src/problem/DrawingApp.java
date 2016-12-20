package problem;

import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class DrawingApp {
	public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Configuration.osToFactory= new HashMap<String, IRendererFactory>();

		Configuration.osToFactory.put("windows", new WindowsRendererFactory());
		Configuration.osToFactory.put("ubuntu", new LinuxRendererFactory());
		Configuration.osToFactory.put("osx", new OSXRendererFactory());
		Configuration.setTestMode(true);
		Configuration.setDefaultOS(Configuration.WINDOWS);

		// Also try this configuration and comment out previous two lines
		//		Configuration.setTestMode(false);



		Window window = new Window("This is a Window", new Rectangle(200,200, 500, 500));
		Label label = new Label(window, "This is a label", new Rectangle(5,30, 200, 25));
		TextBox text = new TextBox("This is a textbox", new Rectangle(300,50, 100, 25));


		IRendererFactory fac = Configuration.getRendererFactory();

		IRenderer Rwindow = fac.createRenderer(window);
		IRenderer Rlabel = fac.createRenderer(label);
		IRenderer RTextBox = fac.createRenderer(text);

		window.setRenderer(Rwindow);
		label.setRenderer(Rlabel);

		window.addChild(label);

		window.show();

		for(int i = 10; i <= 100; i+=10) {
			Thread.sleep(200);
			label.setBounds(new Rectangle(5 + i, 30 + i, 300, 25));
		}

		text.setRenderer(RTextBox);
		window.addChild(text);

	}
}
