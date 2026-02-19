package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPurchase;
import lotto.domain.WinningNumbers;

public class InputView {
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public int readPurchaseAmount() {
		while (true) {
			try {
				System.out.println("구입금액을 입력해 주세요.");
				int amount = parseInt(readLine());
				LottoPurchase.validateAmount(amount);
				return amount;
			} catch (IllegalArgumentException exception) {
				printError(exception.getMessage());
			}
		}
	}

	public int readManualCount(int amount) {
		while (true) {
			try {
				System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
				int manualCount = parseInt(readLine());
				LottoPurchase.validateManualCount(amount, manualCount);
				return manualCount;
			} catch (IllegalArgumentException exception) {
				printError(exception.getMessage());
			}
		}
	}

	public List<Lotto> readManualNumbers(int count) {
		if (count <= 0) {
			return List.of();
		}
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		List<Lotto> numbers = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			numbers.add(readManualNumbersLine());
		}
		LottoPurchase.validateManualLottosCount(count, numbers);
		return numbers;
	}

	public WinningNumbers readWinningNumbers() {
		Lotto winningNumbers = readWinningLotto();
		while (true) {
			try {
				System.out.println("보너스 볼을 입력해 주세요.");
				int bonusNumber = parseInt(readLine());
				return WinningNumbers.of(winningNumbers, LottoNumber.from(bonusNumber));
			} catch (IllegalArgumentException exception) {
				printError(exception.getMessage());
			}
		}
	}

	private Lotto readWinningLotto() {
		while (true) {
			try {
				System.out.println("지난 주 당첨 번호를 입력해 주세요.");
				List<Integer> numbers = parseNumbers(readLine());
				return Lotto.from(numbers);
			} catch (IllegalArgumentException exception) {
				printError(exception.getMessage());
			}
		}
	}

	private String readLine() {
		try {
			String line = reader.readLine();
			if (line == null) {
				throw new IllegalStateException("입력이 종료되었습니다.");
			}
			return line;
		} catch (IOException exception) {
			throw new IllegalStateException("입력을 읽을 수 없습니다.");
		}
	}

	private int parseInt(String input) {
		try {
			return Integer.parseInt(input.trim());
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
		}
	}

	private List<Integer> parseNumbers(String input) {
		return Arrays.stream(input.split(","))
			.map(String::trim)
			.map(this::validateToken)
			.map(this::parseInt)
			.collect(Collectors.toList());
	}

	private String validateToken(String token) {
		if (token.isEmpty()) {
			throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
		}
		if (token.chars().anyMatch(Character::isWhitespace)) {
			throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
		}
		return token;
	}

	private Lotto readManualNumbersLine() {
		while (true) {
			try {
				List<Integer> numbers = parseNumbers(readLine());
				return Lotto.from(numbers);
			} catch (IllegalArgumentException exception) {
				printError(exception.getMessage());
			}
		}
	}

	private void printError(String message) {
		System.out.println(message);
	}
}
