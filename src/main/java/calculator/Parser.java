package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	private static final String DEFAULT_DELIMITER = ",|:";
	private static final Pattern CUSTOM_DELIMITER_PATTERN =
		Pattern.compile("//(.)\n(.*)");

	public String[] split(String input) {
		Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
		String delimiter = DEFAULT_DELIMITER;

		if (matcher.find()) {
			delimiter += "|" + Pattern.quote(matcher.group(1));
			input = matcher.group(2);
		}
		return input.split(delimiter);
	}

}
