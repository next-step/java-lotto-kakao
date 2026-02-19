package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import lotto.exception.LottoValidationException;

class LottoNumberTest {
	@DisplayName("로또 번호가 범위를 벗어나면 LottoValidationException이 발생해야 한다")
	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 46})
	void from_withOutOfRangeValue_throwsLottoValidationException(int value) {
		assertThatThrownBy(() -> LottoNumber.from(value))
			.isInstanceOf(LottoValidationException.class);
	}
}
