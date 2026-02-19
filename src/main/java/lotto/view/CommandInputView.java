package lotto.view;

import java.util.Scanner;

public class CommandInputView implements InputView {
    private Scanner scanner;

    public CommandInputView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String inputManualLottoCount() {
        try {
            String input = scanner.nextLine();
            Integer.parseInt(input);
            return input;
        } catch (Exception e) {
            throw new IllegalArgumentException("구입 정보가 숫자 형식이 아닙니다.");
        }
    }

    @Override
    public String inputPrice() {
        try {
            String input = scanner.nextLine();
            Integer.parseInt(input);
            return input;
        } catch (Exception e) {
            throw new IllegalArgumentException("가격 정보가 숫자 형식이 아닙니다.");
        }
    }

    @Override
    public String inputManualLotto() {
        return scanner.nextLine();
    }

    @Override
    public String inputWinningLotto() {
        return scanner.nextLine();
    }

    @Override
    public String inputBonusNumber() {
        try {
            String input = scanner.nextLine();
            Integer.parseInt(input);
            return input;
        } catch (Exception e) {
            throw new IllegalArgumentException("보너스 번호가 숫자 형식이 아닙니다.");
        }
    }
}
