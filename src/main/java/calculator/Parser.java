package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	private static final String DEFAULT_DELIMITER = ",|:";
	private static final Pattern CUSTOM_DELIMITER_PATTERN =
		Pattern.compile("//(.)\n(.*)");

	public String[] split(String input) {
		String delimiter = DEFAULT_DELIMITER;

		Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);

		if (matcher.find()) {
			String customDelimiter = matcher.group(1);
			delimiter = DEFAULT_DELIMITER + "|" + Pattern.quote(customDelimiter);
			input = matcher.group(2);
		}

		return input.split(delimiter);
	}
}
