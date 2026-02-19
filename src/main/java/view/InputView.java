package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner SCANNER = new Scanner(System.in);

    public int getNumber() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자를 입력하세요.");
        }
    }

    public List<Integer> getLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        try {
            String[] inputs = SCANNER.nextLine().split(",");
            for (String input : inputs) {
                int number = Integer.parseInt(input.trim());
                lottoNumbers.add(number);
            }
            return lottoNumbers;
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자와 ','만 입력해주세요.");
        }
    }
}
