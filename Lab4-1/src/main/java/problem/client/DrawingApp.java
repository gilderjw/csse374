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

public class DrawingApp implements IApplication {
	@Autowired Window window;
	@Autowired Label label;
	@Autowired TextBox text;
	@Autowired Button button;
	
	@Override
	public void execute(String... args) throws Exception {
		window.setTitle("This is a Window");
		window.setBounds(new Rectangle(200,200, 500, 500));
		window.show();
		
		label.setText("This is a label");
		label.setBounds(new Rectangle(5,30, 200, 25));
		window.addChild(label);
		
		for(int i = 10; i <= 100; i+=10) {
			Thread.sleep(200);
			label.setBounds(new Rectangle(5 + i, 30 + i, 300, 25));
		}
		
		text.setText("This is a text");
		text.setBounds(new Rectangle(300,50, 100, 25));
		window.addChild(text);

		button.setText("This is a button");
		button.setBounds(new Rectangle(140,400, 100, 25));
		window.addChild(button);
	}
	
	public static void main(String... args) throws Exception {
		// We will not inject any dependencies for classes in java or javax packages
		WiredConfiguration configurations = WiredConfiguration.getInstance();
		configurations.addExclusion("java", "javax");
		
		IWiredFramework core = WiredFramework.getInstance();
		core.boot(DrawingApp.class, args);
	}	
}
