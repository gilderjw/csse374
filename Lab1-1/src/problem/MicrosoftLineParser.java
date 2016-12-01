package problem;

public class MicrosoftLineParser implements ILineParser {
	@Override
	public String parse(String line) {
		String s = "";
		String[] fields = line.split(",");

		s += (fields[0].trim());
		s += (" : ");
		s += (fields[1].trim());

		return s;
	}
}
