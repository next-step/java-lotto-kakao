package lotto.view.input;

import java.util.Scanner;

public class TerminalInputView implements InputView {
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
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다. 현재 입력: " + line, e);
        }
    }
}
