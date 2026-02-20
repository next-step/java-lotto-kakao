package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {
	@DisplayName("수동 로또 생성기는 입력된 번호로 로또를 생성해야 한다")
	@Test
	void manualLottoGenerator_generate_returnsManualLottoList() {
		ManualLottoGenerator generator = new ManualLottoGenerator(List.of(List.of(6, 1, 2, 3, 4, 5)));

		List<Lotto> lottos = generator.generate();

		assertThat(lottos).hasSize(1);
		assertThat(lottos.getFirst().getNumbers())
			.extracting(LottoNumber::getValue)
			.containsExactly(1, 2, 3, 4, 5, 6);
	}

	@DisplayName("자동 로또 생성기는 요청한 개수만큼 로또를 생성해야 한다")
	@Test
	void randomLottoGenerator_generate_returnsExpectedCount() {
		RandomLottoGenerator generator = new RandomLottoGenerator(2);

		List<Lotto> lottos = generator.generate();

		assertThat(lottos).hasSize(2);
	}

	@DisplayName("랜덤 로또 생성 결과는 정렬되고 중복이 없으며 범위 내여야 한다")
	@Test
	void randomLottoGenerator_generate_returnsSortedUniqueNumbersWithinRange() {
		RandomLottoGenerator generator = new RandomLottoGenerator(1);

		Lotto lotto = generator.generate().getFirst();

		assertThat(lotto.getNumbers())
			.hasSize(6)
			.doesNotHaveDuplicates()
			.isSorted()
			.allMatch(number -> number.getValue() >= 1 && number.getValue() <= 45);
	}

	@DisplayName("수동 로또 생성기 생성 시 입력이 null이면 IllegalArgumentException이 발생해야 한다")
	@Test
	void manualLottoGenerator_constructor_withNull_throwsIllegalArgumentException() {
		assertThatThrownBy(() -> new ManualLottoGenerator(null))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("랜덤 로또 생성기 생성 시 개수가 음수면 IllegalArgumentException이 발생해야 한다")
	@Test
	void randomLottoGenerator_constructor_withNegativeCount_throwsIllegalArgumentException() {
		assertThatThrownBy(() -> new RandomLottoGenerator(-1))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
