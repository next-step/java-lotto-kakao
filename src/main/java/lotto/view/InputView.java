package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Long readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");

        return parseNumber(scanner.nextLine());
    }
    public static int readManualCount(){
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");

        return parseCount(scanner.nextLine());
    }

    public static String readManualNumbers() {
        return scanner.nextLine();
    }

    public static String readWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String readingBonusNumber(){
        System.out.println("보너스 볼을 입력해주세요.");
        return scanner.nextLine();
    }

    private static Long parseNumber(String number) {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해주세요.");
        }
    }

    private static Integer parseCount(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해주세요.");
        }
    }
}