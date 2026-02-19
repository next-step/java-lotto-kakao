package level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public Price inputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();

        return new Price(parseToInt(input));
    }

    public AnswerLottery inputAnswerLottery() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<String> numbers = Arrays.asList(scanner.nextLine().split(",\\s*"));

        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusBall = scanner.nextLine();

        return new AnswerLottery(numbers, bonusBall);
    }

    public int inputManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return parseToInt(scanner.nextLine());
    }


    public List<String> inputManualNumbers() {
        return Arrays.asList(scanner.nextLine().split(",\\s*"));
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }
}
