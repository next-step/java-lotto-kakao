package lotto.view.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalInputView implements InputView {

    public static final String INVALID_NUMBER_FORMAT_MSG_PREFIX = "숫자만 입력할 수 있습니다. 현재 입력: ";
    public static final String DEFAULT_DELIMITER = ",";

    private Scanner scanner;

    public TerminalInputView() {
        this.scanner = new Scanner(System.in);
    }

    public String input() {
        return scanner.nextLine();
    }

    @Override
    public int inputNumber() {
        String line = scanner.nextLine().trim();

        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            // 요구사항: 문자가 입력되면 예외를 던진다
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT_MSG_PREFIX + line, e);
        }
    }

    @Override
    public List<Integer> inputNumbers() {
        String line = scanner.nextLine().trim();
        List<Integer> result = new ArrayList<>();
        try {
            String[] numbers = line.split(DEFAULT_DELIMITER);
            for (String number: numbers) {
                result.add(Integer.parseInt(number));
            }
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT_MSG_PREFIX + line, e);
        }
    }
}
