package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private InputView() {
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static Long readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");

        return parseLong(scanner.nextLine());
    }

    public static String readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static int readManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return parseNumber(scanner.nextLine());
    }

    public static List<String> readManualNumbers(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    public static int readingBonusNumber() {
        System.out.println("보너스 볼을 입력해주세요.");
        return parseNumber(scanner.nextLine());
    }

    private static Long parseLong(String number) {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해주세요.");
        }
    }

    private static int parseNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해주세요.");
        }
    }
}