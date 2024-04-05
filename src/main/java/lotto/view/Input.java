package lotto.view;

import java.util.Scanner;

public class Input {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Input() {
    }

    public static Long getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return Long.parseLong(SCANNER.nextLine());
    }

    public static Long getManualPurchaseCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Long.parseLong(SCANNER.nextLine());
    }

    public static String getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(SCANNER.nextLine());
    }
}
