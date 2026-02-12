package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import lotto.domain.Lotto;

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
			manualNumbers.add(parseNumbers(readLine(), Lotto.REQUIRED_SIZE));
		}
		return manualNumbers;
	}

	public List<Integer> readWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return parseNumbers(readLine(), Lotto.REQUIRED_SIZE);
	}

	public int readBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return parseInt(readLine());
	}

	private String readLine() {
		try {
			return reader.readLine();
		} catch (IOException exception) {
			throw new IllegalStateException("Failed to read input.");
		}
	}

	private int parseInt(String input) {
		String trimmed = validateAndTrim(input);
		try {
			return Integer.parseInt(trimmed);
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException("Input must be a number.");
		}
	}

	private List<Integer> parseNumbers(String input, int expectedSize) {
		String trimmed = validateAndTrim(input);
		String[] tokens = trimmed.split(",");
		List<Integer> numbers = new ArrayList<>();
		for (String token : tokens) {
			String value = token.trim();
			if (value.isEmpty()) {
				throw new IllegalArgumentException("Input must be comma-separated numbers.");
			}
			try {
				numbers.add(Integer.parseInt(value));
			} catch (NumberFormatException exception) {
				throw new IllegalArgumentException("Input must be comma-separated numbers.");
			}
		}
		if (numbers.size() != expectedSize) {
			throw new IllegalArgumentException(
				String.format("Input must contain %d numbers.", expectedSize)
			);
		}
		return numbers;
	}

	private String validateAndTrim(String input) {
		if (input == null) {
			throw new IllegalArgumentException("Input must not be null.");
		}
		String trimmed = input.trim();
		if (trimmed.isEmpty()) {
			throw new IllegalArgumentException("Input must not be blank.");
		}
		return trimmed;
	}
}
