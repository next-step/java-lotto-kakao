package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoMachineTest {
	@DisplayName("로또 발급 시 금액에 따라 발급 개수가 결정되어야 한다")
	@ParameterizedTest
	@CsvSource({
		"1000, 1",
		"1500, 1",
		"2500, 2"
	})
	void issue_withAmount_returnsExpectedTicketCount(int amount, int expectedCount) {
		LottoMachine machine = new LottoMachine();

		LottoPurchase purchase = machine.issue(amount, 0, List.of());

		assertThat(purchase.getLottos()).hasSize(expectedCount);
		assertThat(purchase.getAutoCount()).isEqualTo(expectedCount);
		assertThat(purchase.getManualCount()).isZero();
	}

	@DisplayName("발급된 로또 번호는 정렬되고 중복이 없으며 범위 내여야 한다")
	@Test
	void issue_withAmount_returnsSortedUniqueNumbersWithinRange() {
		LottoMachine machine = new LottoMachine();

		LottoPurchase purchase = machine.issue(1_000, 0, List.of());
		Lotto lotto = purchase.getLottos().getFirst();

		assertThat(lotto.getNumbers())
			.hasSize(6)
			.doesNotHaveDuplicates()
			.isSorted()
			.allMatch(number -> number.getValue() >= 1 && number.getValue() <= 45);
	}
}
