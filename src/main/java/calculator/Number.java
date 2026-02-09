package calculator;

public class Number {

    private static int value;
    public Number(String text) {
        if (text == null) {
            value = 0;
            return;
        }
        if (text.isEmpty()) {
            value = 0;
        }
    }

    public int getValue() {
        return value;
    }
}
