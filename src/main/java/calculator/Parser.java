package calculator;

public class Parser {
    private static String delimiter = ",|:";

    public String[] split(String input) {
        return input.split(delimiter);
    }
}
