package problem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrouponLineParser implements ILineParser {

	@Override
	public String parse(String line) {
		Pattern p = Pattern.compile("(.*?)\\s+(.*)");
		Matcher m = p.matcher(line);

		if(m.find()){
			String s =  m.group(1) + " : " + m.group(2);

			return s;
		}
		return null;
	}
}
