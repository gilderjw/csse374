package problem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The DataStandardizer class standardizes the Business Intelligence data provided by
 * Google and Microsoft to a common format.
 *
 * @author Chandan R. Rupakheti
 * @author Mark Hays
 */
public class DataStandardizer {
	// The main method
	public static void main(String[] args) {
		DataStandardizer unifier = new DataStandardizer();
		unifier.execute();
	}
	protected String company;
	protected String data;

	protected JFrame frame;
	protected JPanel topPanel;
	protected JTextField txtField;

	protected JButton button;
	protected JScrollPane scrollPane;
	protected JTextArea textArea;

	protected JLabel label;

	protected HashMap<String, ILineParser> parsers = new HashMap<String, ILineParser>();

	public DataStandardizer() {
		this.addParser("google", new GoogleLineParser());
		this.addParser("microsoft", new MicrosoftLineParser());
	}

	public void addParser(String s, ILineParser p){
		this.parsers.put(s, p);
	}

	protected void createAndShowGUI() {
		this.frame = new JFrame("Data Unifier");

		this.topPanel = new JPanel();
		this.txtField = new JTextField("./input_output/io.gogl");
		this.txtField.setPreferredSize(new Dimension(200,25));
		this.topPanel.add(this.txtField);

		this.button = new JButton("Unify!");
		this.topPanel.add(this.button);

		// Add the top panel to the top of the window
		this.frame.add(this.topPanel, BorderLayout.NORTH);


		this.textArea = new JTextArea(5, 60);
		this.textArea.setPreferredSize(new Dimension(300, 200));
		this.scrollPane = new JScrollPane(this.textArea);

		// Add the scroll pane to the center of the window
		this.frame.add(this.scrollPane, BorderLayout.CENTER);

		// Add the label as status
		this.label = new JLabel("<No Data>");
		this.frame.add(this.label, BorderLayout.SOUTH);

		// Add action listener to the button
		this.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Parse the source file
				DataStandardizer.this.parse(DataStandardizer.this.txtField.getText());
				DataStandardizer.this.label.setText("Company: " + DataStandardizer.this.getCompany());
				DataStandardizer.this.textArea.setText(DataStandardizer.this.getData());
			}
		});


		this.frame.pack();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}

	public void execute() {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Basically, shows up the GUI.
				DataStandardizer.this.createAndShowGUI();
			}
		});
	}


	public String getCompany() {
		return this.company;
	}

	public String getData() {
		return this.data;
	}

	public void parse(String path) {
		Charset charset = Charset.forName("US-ASCII");
		ILineParser parser;
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), charset)) {

			// First line represents the name of a company
			this.company = reader.readLine();
			parser = this.parsers.get(this.company);

			// The rest is the data
			StringBuffer buffer = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(parser.parse(line));
				buffer.append("\n");
			}

			// Done unifying the data
			this.data = buffer.toString();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}
}
