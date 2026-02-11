package calculator;

public class StringCalculator {
    public static int add(String input) {
        if (Input.isBlank(input)){
            return 0;
        }

        ParsedInput parsed = InputParser.parse(input);
        return SumCalculator.sum(parsed.body(), parsed.delimiterRegex());
    }
}
