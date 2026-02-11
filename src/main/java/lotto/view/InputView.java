package lotto.view;

import lotto.model.LottoNumber;
import lotto.model.LottoTicket;
import lotto.model.Money;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = Integer.parseInt(scanner.nextLine().trim());
        return new Money(amount);
    }

    public LottoTicket inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine().trim();
        List<LottoNumber> numbers = parseNumbers(input);
        return new LottoTicket(numbers);
    }

    public LottoNumber inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int number = Integer.parseInt(scanner.nextLine().trim());
        return new LottoNumber(number);
    }

    private List<LottoNumber> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

}
