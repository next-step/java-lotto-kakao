package lotto.view;

public class LottoInputValidator {

    public static int parseInt(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
}
