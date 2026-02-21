package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int enterPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        System.out.println();

        validateNumber(input);

        int price = Integer.parseInt(input);
        if (price <= 0) {
            throw new IllegalArgumentException("구입 금액은 양수여야 합니다.");
        }
        return price;
    }

    public int enterManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();
        System.out.println();

        validateNumber(input);
        return Integer.parseInt(input);
    }

    public List<List<Integer>> enterManualLottosNumbers(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> lottosNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String input = scanner.nextLine();
            lottosNumbers.add(getNumbers(input));
        }
        System.out.println();
        return lottosNumbers;
    }

    public List<Integer> enterWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return parseNumbers(input);
    }

    private List<Integer> parseNumbers(String input) {
        String[] parts = input.split(",");
        for (String part : parts) {
            validateNumber(part);
        }
        List<Integer> winningNumbers = new ArrayList<>();
        for (String part : parts){
            winningNumbers.add(Integer.parseInt(part.trim()));
        }
        return winningNumbers;
    }

    public int enterBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        System.out.println();

        validateNumber(input);

        return Integer.parseInt(input);
    }

    private List<Integer> getNumbers(String str) {
        String[] parts = str.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String part : parts) {
            validateNumber(part);
            numbers.add(Integer.parseInt(part.trim()));
        }
        return numbers;
    }

    private void validateNumber(String str) {
        if(!str.trim().matches("-?\\d+")){
            throw new IllegalArgumentException("입력 값이 정수가 아닙니다.");
        }
    }
}
