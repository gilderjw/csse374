package problem.client;

import java.awt.Rectangle;

import edu.rosehulman.csse374.wired.annotations.Autowired;
import edu.rosehulman.csse374.wired.api.IApplication;
import edu.rosehulman.csse374.wired.api.IWiredFramework;
import edu.rosehulman.csse374.wired.core.WiredConfiguration;
import edu.rosehulman.csse374.wired.core.WiredFramework;
import problem.components.Button;
import problem.components.Label;
import problem.components.TextBox;
import problem.components.Window;
import problem.renderers.Configuration;

public class DrawingApp implements IApplication {
	public static void main(String... args) throws Exception {
		// We will not inject any dependencies for classes in java or javax packages
		WiredConfiguration configurations = WiredConfiguration.getInstance();
		configurations.addExclusion("java", "javax");

		IWiredFramework core = WiredFramework.getInstance();

		if ((args.length > 0) && (args[0].equals("test"))){
			Configuration.getInstance().setDefaultOS("win");
			core.boot(DrawingApp.class, args);

			Configuration.getInstance().setDefaultOS("mac");
			core.boot(DrawingApp.class, args);

			Configuration.getInstance().setDefaultOS("lin");
			core.boot(DrawingApp.class, args);
		} else {
			Configuration.getInstance();
			core.boot(DrawingApp.class, args);
		}
	}
	@Autowired Window window;
	@Autowired Label label;
	@Autowired TextBox text;

	@Autowired Button button;

	@Override
	public void execute(String... args) throws Exception {

		this.window.setTitle("This is a Window");
		this.window.setBounds(new Rectangle(200,200, 500, 500));
		this.window.show();

		this.label.setText("This is a label");
		this.label.setBounds(new Rectangle(5,30, 200, 25));
		this.window.addChild(this.label);

		for(int i = 10; i <= 100; i+=10) {
			Thread.sleep(200);
			this.label.setBounds(new Rectangle(5 + i, 30 + i, 300, 25));
		}

		this.text.setText("This is a text");
		this.text.setBounds(new Rectangle(300,50, 100, 25));
		this.window.addChild(this.text);

		this.button.setText("This is a button");
		this.button.setBounds(new Rectangle(140,400, 100, 25));
		this.window.addChild(this.button);

	}
}
