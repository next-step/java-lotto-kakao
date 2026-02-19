package level0;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    public static final List<String> DEFAULT_DELIMINATORS = List.of(":", ",");

    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.)\\\\n(.*)");

    private final String inputString;

    private final Set<String> deliminators;

    private final Numbers numbers;

    public Input(String inputString) {
        this.inputString = inputString;
        this.deliminators = parseDeliminators(inputString);
        this.numbers = parseNumbers(inputString);
    }

    private Set<String> parseDeliminators(String inputString) {
        Set<String> deliminators = new HashSet<>(
                DEFAULT_DELIMINATORS
        );

        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(inputString);
        if (matcher.find()) {
            String customDelimiter = matcher.group(1);
            deliminators.add(customDelimiter);
        }
        return deliminators;
    }

    private Numbers parseNumbers(String inputString) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(inputString);
        if (matcher.find()) {
            inputString = matcher.group(2);
        }

        String[] inputs = inputString.split(buildSplitter());
        List<Number> numberList = new ArrayList<>();

        for (String input : inputs) {
            Number number = new Number(input);
            numberList.add(number);
        }

        return new Numbers(numberList);
    }

    private String buildSplitter() {
        StringBuilder sb = new StringBuilder();
        for (String deliminator : deliminators) {
            sb.append(deliminator).append("|");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public Set<String> getDeliminators() {
        return deliminators;
    }

    public Numbers getNumbers() {
        return numbers;
    }
}
