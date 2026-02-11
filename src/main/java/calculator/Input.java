package calculator;

public class Input {

    private Input() {
    }

    public static boolean isBlank(String input) {
        if (input == null) {
            return true;
        }
        return input.isEmpty();
    }


}
