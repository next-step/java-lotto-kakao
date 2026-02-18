package lotto.view;

import lotto.model.LottoNumber;
import lotto.model.LottoTicket;
import lotto.model.LottoTickets;
import lotto.model.Money;

import java.util.ArrayList;
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
        try {
            int amount = Integer.parseInt(scanner.nextLine().trim());
            return new Money(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해 주세요.");
        }
    }

    public int inputManualTicketCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해 주세요.");
        }
    }

    public LottoTickets inputManualTickets(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<LottoTicket> manualTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String input = scanner.nextLine().trim();
            List<LottoNumber> numbers = parseNumbers(input);
            manualTickets.add(new LottoTicket(numbers));
        }
        return new LottoTickets(manualTickets);
    }

    public LottoTicket inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine().trim();
        List<LottoNumber> numbers = parseNumbers(input);
        return new LottoTicket(numbers);
    }

    public LottoNumber inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            int number = Integer.parseInt(scanner.nextLine().trim());
            return new LottoNumber(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해 주세요.");
        }
    }

    private List<LottoNumber> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 숫자 형식이 아닙니다.");
        }
    }
}
