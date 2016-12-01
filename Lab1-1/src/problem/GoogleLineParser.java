package problem;

public class GoogleLineParser implements ILineParser {

	@Override
	public String parse(String line) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(line.replace('-', ':'));
		buffer.append("\n");
		return buffer.toString();
	}

}
