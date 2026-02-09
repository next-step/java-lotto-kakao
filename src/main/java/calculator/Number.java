package calculator;

public class Number {

    private static int value;
    public Number(String text) {
        if (text == null || text.isEmpty()) {
            value = 0;
            return;
        }
        parseInt(text);
        if (value < 0) throw new IllegalArgumentException();

    }

    private static void parseInt(String text) {
        try {
            value = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public int getValue() {
        return value;
    }
}
