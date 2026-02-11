package level1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import level1.domain.AnswerLottery;

public class InputHandler {

    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int inputPrice() {
        int price;

        System.out.println("구입금액을 입력해 주세요.");
        try {
            price = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            throw new RuntimeException("올바르지 않은 형식입니다.");
        }

        if (price < 1000) {
            throw new RuntimeException("1,000 원 이하는 구매할 수 없습니다.");
        }

        return price;
    }

    public AnswerLottery inputAnswerLottery() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<String> numbers = Arrays.asList(scanner.nextLine().split(",\\s*"));

        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusBall = scanner.nextLine();

        return new AnswerLottery(numbers, bonusBall);
    }
}
