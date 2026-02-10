package lotto.view.input;

import java.util.Scanner;

public class CommandInputView implements InputView {
    private Scanner scanner;

    public CommandInputView() {
        this.scanner = new Scanner(System.in);
    }

    public String input() {
        return scanner.nextLine();
    }
}
