package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public int readPurchaseAmount() {
		System.out.println("구입금액을 입력해 주세요.");
		return parseInt(readLine());
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
			throw new IllegalStateException("Failed to read input.");
		}
	}

	private int parseInt(String input) {
		return Integer.parseInt(input.trim());
	}

	private List<Integer> parseNumbers(String input) {
		return Arrays.stream(input.split(","))
			.map(String::trim)
			.filter(value -> !value.isEmpty())
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}
}
