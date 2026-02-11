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
}
