package lotto;

import java.util.ArrayList;
import java.util.List;

public class Parser {
	private static final String DELIMITER_REGEX = "\\s*,\\s*";
	private static final int LOTTO_SIZE = 6;

	public List<Ball> parse(String input) {
		String[] tokens = input.split(DELIMITER_REGEX);
		validateSize(tokens);

		List<Ball> balls = new ArrayList<>();
		for (String token : tokens) {
			balls.add(new Ball(parseInt(token)));
		}
		return balls;
	}

	private void validateSize(String[] tokens) {
		if (tokens.length != LOTTO_SIZE) {
			throw new IllegalArgumentException("입력이 잘못되었습니다.");
		}
	}

	private int parseInt(String token) {
		try {
			return Integer.parseInt(token);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("로또 번호는 숫자만 입력 가능합니다.");
		}
	}

	public Ball parseBall(String input) {
		return new Ball(parseInt(input.trim()));
	}

	public List<List<Ball>> parseManualLottos(List<String> inputs) {
		List<List<Ball>> result = new ArrayList<>();
		for (String input : inputs) {
			result.add(parse(input));
		}
		return result;
	}

}
