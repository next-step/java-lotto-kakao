package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int enterPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();

        validateNumber(input);

        int amount = Integer.parseInt(input);
        if (amount <= 0) {
            throw new IllegalArgumentException("구입 금액은 양수여야 합니다.");
        }
        return amount;
    }

    public int enterManualPurchaseCount() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();

        validateNumber(input);

        int count = Integer.parseInt(input);
        if (count < 0) {
            throw new IllegalArgumentException("수동 구매 수는 음수일 수 없습니다.");
        }
        return count;
    }

    public List<List<Integer>> enterManualLottos(int count) {
        if (count == 0) {
            return new ArrayList<>();
        }
        System.out.println();
        List<List<Integer>> manualNumbers = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < count; i++) {
            String input = scanner.nextLine();
            manualNumbers.add(parseNumbers(input));
        }
        return manualNumbers;
    }

    public List<Integer> enterWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return parseNumbers(input);
    }

    private List<Integer> parseNumbers(String input) {
        String[] parts = input.split(",");
        List<Integer> winningNumbers = new ArrayList<>();
        for (String part : parts){
            validateNumber(part);
            winningNumbers.add(Integer.parseInt(part.trim()));
        }
        return winningNumbers;
    }

    public int enterBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        validateNumber(input);

        return Integer.parseInt(input);
    }

    private void validateNumber(String str) {
        if(!str.trim().matches("-?\\d+")){
            throw new IllegalArgumentException("입력 값이 정수가 아닙니다.");
        }
    }
}
