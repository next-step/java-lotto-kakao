package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
	@DisplayName("로또 번호가 범위를 벗어나면 IllegalArgumentException이 발생해야 한다")
	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 46})
	void from_withOutOfRangeValue_throwsIllegalArgumentException(int value) {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> LottoNumber.from(value));
	}
}
