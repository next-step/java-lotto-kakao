package lotto.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        String line = readTrimmedLine();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT_MSG_PREFIX + line, e);
        }
    }

    @Override
    public List<Integer> inputNumbers() {
        return parseNumbersOrThrow(readTrimmedLine());
    }

    private String readTrimmedLine() {
        return scanner.nextLine().trim();
    }

    private int parseIntOrThrow(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT_MSG_PREFIX + token, e);
        }
    }

    private List<Integer> parseNumbersOrThrow(String line) {
        try {
            return Arrays.stream(line.split(DEFAULT_DELIMITER))
                    .map(String::trim)
                    .map(this::parseIntOrThrow)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
