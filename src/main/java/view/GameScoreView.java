package view;

import model.LottoNumber;
import model.LottoNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameScoreView {

    private final Scanner SCANNER = new Scanner(System.in);

    public void showInputWinNumberMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public LottoNumbers inputWinNumber() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        try {
            String[] input = SCANNER.nextLine().split(",");
            for (String string : input) {
                int number = Integer.parseInt(string.trim());
                lottoNumbers.add(new LottoNumber(number));
            }
            return new LottoNumbers(lottoNumbers);
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자와 ','만 입력해주세요.");
        }
    }

    public void showInputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public LottoNumber inputBonusBall() {
        int number;
        try {
            number = Integer.parseInt(SCANNER.nextLine());
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
        return new LottoNumber(number);
    }

    public void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[Error] " + e.getMessage());
    }
}
