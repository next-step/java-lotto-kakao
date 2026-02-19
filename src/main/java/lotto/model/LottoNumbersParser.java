package lotto.model;

import java.util.Arrays;
import java.util.List;

public class LottoNumbersParser {

	private static final String DELIMITER = ",";

	public Lotto parse(String input) {
		try {
			List<LottoNumber> numbers = Arrays.stream(input.split(DELIMITER))
				.map(String::trim)
				.map(Integer::parseInt)
				.map(LottoNumber::new)
				.toList();
			return new Lotto(numbers);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("로또 번호는 숫자만 입력할 수 있습니다.");
		}
	}
}
