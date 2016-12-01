package problem;

public class MicrosoftLineParser implements ILineParser {
	@Override
	public String parse(String line) {
		StringBuffer buffer = new StringBuffer();
		String[] fields = line.split(",");
		buffer.append(fields[0].trim());
		buffer.append(" : ");
		buffer.append(fields[1].trim());
		return null;
	}
}
