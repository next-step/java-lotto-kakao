package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private final Scanner scanner = new Scanner(System.in);

	public Money readMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return new Money(parseInteger(scanner.nextLine()));
	}

	public LottoTicket readWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return new LottoTicket(parseLottoNumbers(scanner.nextLine()));
	}

	public LottoNumber readBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return LottoNumber.from(parseInteger(scanner.nextLine()));
	}

	public int readManualLottoCount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return parseInteger(scanner.nextLine());
	}

	public LottoTickets readManualLottoTickets(int count) {
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		List<LottoTicket> tickets = new ArrayList<>();
		while (tickets.size() < count) {
			readManualLottoTicket(tickets);
		}
		return new LottoTickets(tickets);
	}

	private void readManualLottoTicket(List<LottoTicket> tickets) {
		try {
			tickets.add(new LottoTicket(parseLottoNumbers(scanner.nextLine())));
		} catch (IllegalArgumentException exception) {
			printError(exception.getMessage());
		}
	}

	private List<LottoNumber> parseLottoNumbers(String input) {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		for (String token : input.split(",")) {
			lottoNumbers.add(parseLottoNumber(token));
		}
		return lottoNumbers;
	}

	private LottoNumber parseLottoNumber(String token) {
		return LottoNumber.from(parseInteger(token));
	}

	private int parseInteger(String input) {
		try {
			return Integer.parseInt(input.trim());
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
		}
	}

	private void printError(String message) {
		System.out.printf("[ERROR] %s%n", message);
	}
}
