package csse374.textprocessor;

public class TextProcessingApp {
	public static void main(String[] args) {
		String text = "This is a sample text. It will be processed by this application.";

//		 Transformer transformer = new AllCapsTransformer();
		// OR use another one
		Transformer transformer = new SubstitutionTransformer("processed", "transformed");
		
		TextProcessor processor = new TextProcessor(text, transformer);
		ProcessorUI ui = new ProcessorUI(processor);
		
		ui.show();
	}
}
