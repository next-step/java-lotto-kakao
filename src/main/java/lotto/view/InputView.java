package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import lotto.domain.Lotto;
import lotto.exception.LottoInputException;
import lotto.exception.LottoSystemException;

public class InputView {
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public int readPurchaseAmount() {
		System.out.println("구입금액을 입력해 주세요.");
		return parseInt(readLine());
	}

	public int readManualCount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return parseInt(readLine());
	}

	public List<List<Integer>> readManualNumbers(int manualCount) {
		if (manualCount <= 0) {
			return List.of();
		}

		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		List<List<Integer>> manualNumbers = new ArrayList<>();
		for (int i = 0; i < manualCount; i++) {
			manualNumbers.add(parseNumbers(readLine()));
		}
		return manualNumbers;
	}

	public List<Integer> readWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return parseNumbers(readLine());
	}

	public int readBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return parseInt(readLine());
	}

	private String readLine() {
		try {
			return reader.readLine();
		} catch (IOException exception) {
			throw new LottoSystemException("입력을 읽는 중 오류가 발생했습니다.", exception);
		}
	}

	private int parseInt(String input) {
		String trimmed = validateAndTrim(input);
		try {
			return Integer.parseInt(trimmed);
		} catch (NumberFormatException exception) {
			throw new LottoInputException("숫자를 입력해 주세요.");
		}
	}

	private List<Integer> parseNumbers(String input) {
		String trimmed = validateAndTrim(input);
		String[] tokens = trimmed.split(",");
		List<Integer> numbers = new ArrayList<>();
		for (String token : tokens) {
			String value = token.trim();
			if (value.isEmpty()) {
				throw new LottoInputException("쉼표로 구분된 숫자를 입력해 주세요.");
			}
			try {
				numbers.add(Integer.parseInt(value));
			} catch (NumberFormatException exception) {
				throw new LottoInputException("쉼표로 구분된 숫자를 입력해 주세요.");
			}
		}
		if (numbers.size() != Lotto.REQUIRED_SIZE) {
			throw new LottoInputException(
				String.format("번호는 %d개 입력해야 합니다.", Lotto.REQUIRED_SIZE)
			);
		}
		return numbers;
	}

	private String validateAndTrim(String input) {
		if (input == null) {
			throw new LottoInputException("입력이 없습니다.");
		}
		String trimmed = input.trim();
		if (trimmed.isEmpty()) {
			throw new LottoInputException("공백만 입력할 수 없습니다.");
		}
		return trimmed;
	}
}
