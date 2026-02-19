package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public Integer readPurchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        if (!scanner.hasNextLine()) return null;
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("구입금액은 숫자여야 합니다.");
        }
    }

    public Integer readManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        if (!scanner.hasNextLine()) return null;
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("수동 구매 수는 숫자여야 합니다.");
        }
    }

    public List<String> readManualLottoNumbers(int manualLottoCount) {
        List<String> manualLottoInputs = new ArrayList<>();
        if (manualLottoCount <= 0) {
            return manualLottoInputs;
        }

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualLottoCount; i++) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("수동 로또 번호 입력이 부족합니다.");
            }
            manualLottoInputs.add(scanner.nextLine().trim());
        }
        return manualLottoInputs;
    }

    public String readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        if (!scanner.hasNextLine()) return null;
        return scanner.nextLine().trim();
    }

    public String readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        if (!scanner.hasNextLine()) return null;
        return scanner.nextLine().trim();
    }
}
