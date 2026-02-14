package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoParser {

	private static final String DELIMITER = ", ";

	private LottoParser() {
	}

	public static List<LottoNumber> parseLottoNumbers(String input) {
		validateEmpty(input);
		return new ArrayList<>(Arrays.stream(input.split(DELIMITER))
			.map(String::trim)
			.map(LottoParser::toInt)
			.map(LottoNumber::new)
			.toList());
	}

	public static int parseBonusNumber(String input) {
		validateEmpty(input);
		return toInt(input);
	}

	private static int toInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("숫자만 입력 가능합니다.");
		}
	}

	private static void validateEmpty(String input) {
		if (input == null || input.isBlank()) {
			throw new IllegalArgumentException("입력값이 비어있습니다.");
		}
	}
}
