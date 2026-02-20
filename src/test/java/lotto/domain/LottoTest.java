package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
	@DisplayName("로또 번호는 오름차순으로 정렬되어야 한다")
	@Test
	void from_withUnsortedNumbers_sortsNumbers() {
		Lotto lotto = Lotto.from(List.of(5, 1, 3, 2, 4, 6));

		assertThat(lotto.getNumbers())
			.extracting(LottoNumber::getValue)
			.containsExactly(1, 2, 3, 4, 5, 6);
	}

	@DisplayName("로또 번호에 중복이 있으면 IllegalArgumentException이 발생해야 한다")
	@Test
	void from_withDuplicateNumbers_throwsIllegalArgumentException() {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

		assertThatThrownBy(() -> Lotto.from(numbers))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("로또 번호 개수가 6개가 아니면 IllegalArgumentException이 발생해야 한다")
	@Test
	void from_withInvalidSize_throwsIllegalArgumentException() {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5);

		assertThatThrownBy(() -> Lotto.from(numbers))
			.isInstanceOf(IllegalArgumentException.class);
	}

}
