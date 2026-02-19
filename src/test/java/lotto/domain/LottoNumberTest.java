package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
	@DisplayName("로또 번호가 범위를 벗어나면 IllegalArgumentException이 발생해야 한다")
	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 46})
	void from_withOutOfRangeValue_throwsIllegalArgumentException(int value) {
		assertThatThrownBy(() -> LottoNumber.from(value))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("같은 번호를 조회하면 동일한 LottoNumber 인스턴스를 반환한다")
	@Test
	void from_withSameValue_returnsSameInstance() {
		LottoNumber first = LottoNumber.from(7);
		LottoNumber second = LottoNumber.from(7);

		assertThat(first).isSameAs(second);
	}
}
