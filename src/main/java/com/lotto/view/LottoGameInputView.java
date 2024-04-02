package com.lotto.view;

import com.lotto.model.TargetLotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoGameInputView {
    private final Scanner SCANNER = new Scanner(System.in);

    public int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public TargetLotto inputTargetNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<Integer> numbers = Arrays.stream(SCANNER.nextLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = Integer.parseInt(SCANNER.nextLine());
        return new TargetLotto(numbers, bonusNumber);
    }
}
