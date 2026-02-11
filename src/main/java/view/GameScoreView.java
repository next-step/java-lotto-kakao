package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameScoreView {

    private final Scanner SCANNER = new Scanner(System.in);

    public void showInputWinNumberMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public List<Integer> inputWinNumber(Integer ballCount, Integer minBallNumber, Integer maxBallNumber) {
        String rawNumbers = SCANNER.nextLine();
        if (!rawNumbers.matches("^[0-9][0-9, ]*$")) {
            throw new IllegalArgumentException("숫자와 ','만 입력 가능합니다.");
        }
        List<Integer> result = new ArrayList<>();
        for (String rawNumber : rawNumbers.split(",")) {
            int number = Integer.parseInt(rawNumber.trim());
            if (number < minBallNumber || number > maxBallNumber) {
                throw new IllegalArgumentException("1부터 45까지를 입력하세요");
            }
            result.add(number);
        }
        if (result.size() != ballCount) {
            throw new IllegalArgumentException("숫자 6개를 입력해주세요.");
        }
        return result;
    }

    public void showInputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public Integer inputBonusBall(Integer minBallNumber, Integer maxBallNumber) {
        String rawNumber = SCANNER.nextLine();
        if (!rawNumber.matches("[0-9]*$")) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
        Integer result = Integer.parseInt(rawNumber);
        if (result < minBallNumber || result > maxBallNumber) throw new IllegalArgumentException("1부터 45까지를 입력하세요");
        return result;
    }

    public void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[Error] " + e.getMessage());
    }
}
