package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.LottoNumbersParser;

public class LottoNumbersParserTest {

	private final LottoNumbersParser parser = new LottoNumbersParser();

	@Test
	void parseLottoNumberSuccessfully() {
		Lotto lotto = parser.parse("8, 21, 23, 41, 42, 43");

		assertThat(lotto.getNumbers()).isEqualTo(List.of(
			new LottoNumber(8),
			new LottoNumber(21),
			new LottoNumber(23),
			new LottoNumber(41),
			new LottoNumber(42),
			new LottoNumber(43)
		));
	}

	@Test
	void throwExceptionWhenInputIsNotNumeric() {
		assertThatThrownBy(() -> parser.parse("1,2,a,4,5,6"))
			.isInstanceOf(IllegalArgumentException.class);
	}

}
