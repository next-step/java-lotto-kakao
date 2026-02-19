package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import lotto.exception.LottoValidationException;

class LottoMachineTest {
	@DisplayName("로또 발급 시 금액과 수동 개수에 따라 자동 발급 개수가 결정되어야 한다")
	@ParameterizedTest
	@CsvSource({
		"1000, 0, 1",
		"1500, 0, 1",
		"2500, 1, 1",
		"2500, 2, 0"
	})
	void calculateRandomCountFromAmount_withAmountAndManualCount_returnsExpectedAutoCount(int amount, int manualCount, int expectedCount) {
		LottoMachine machine = new LottoMachine();

		int count = machine.calculateRandomCountFromAmount(amount, manualCount);

		assertThat(count).isEqualTo(expectedCount);
	}

	@DisplayName("자동 발급 계산 시 금액이 가격 미만이면 LottoValidationException이 발생해야 한다")
	@Test
	void calculateRandomCountFromAmount_withAmountLessThanPrice_throwsLottoValidationException() {
		LottoMachine machine = new LottoMachine();

		assertThatThrownBy(() -> machine.calculateRandomCountFromAmount(999, 0))
			.isInstanceOf(LottoValidationException.class);
	}

	@DisplayName("발급된 로또 번호는 정렬되고 중복이 없으며 범위 내여야 한다")
	@Test
	void issue_withAmount_returnsSortedUniqueNumbersWithinRange() {
		LottoMachine machine = new LottoMachine();

		Lotto lotto = machine.issueRandom(1).getFirst();

		assertThat(lotto.getNumbers())
			.hasSize(6)
			.doesNotHaveDuplicates()
			.isSorted()
			.allMatch(number -> number.getValue() >= 1 && number.getValue() <= 45);
	}

	@DisplayName("자동 발급 계산 시 수동 개수가 음수면 LottoValidationException이 발생해야 한다")
	@Test
	void calculateRandomCountFromAmount_withNegativeManualCount_throwsLottoValidationException() {
		LottoMachine machine = new LottoMachine();

		assertThatThrownBy(() -> machine.calculateRandomCountFromAmount(1_000, -1))
			.isInstanceOf(LottoValidationException.class);
	}

	@DisplayName("자동 발급 계산 시 수동 개수가 전체 발급 개수를 초과하면 LottoValidationException이 발생해야 한다")
	@Test
	void calculateRandomCountFromAmount_withManualCountExceedingTotal_throwsLottoValidationException() {
		LottoMachine machine = new LottoMachine();

		assertThatThrownBy(() -> machine.calculateRandomCountFromAmount(1_000, 2))
			.isInstanceOf(LottoValidationException.class);
	}

	@DisplayName("자동 발급 개수에 따라 발급 개수가 결정되어야 한다")
	@ParameterizedTest
	@CsvSource({
		"0, 0",
		"1, 1",
		"2, 2"
	})
	void issueRandom_withCount_returnsExpectedTicketCount(int count, int expectedCount) {
		LottoMachine machine = new LottoMachine();

		List<Lotto> lottos = machine.issueRandom(count);

		assertThat(lottos).hasSize(expectedCount);
	}
}
