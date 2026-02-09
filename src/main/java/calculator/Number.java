package calculator;

public class Number {

    private static int value;
    public Number(String text) {
        if (text == null || text.isEmpty()) {
            value = 0;
            return;
        }
        value = Integer.parseInt(text);

    }

    public int getValue() {
        return value;
    }
}
