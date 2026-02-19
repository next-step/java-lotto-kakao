package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoPurchasePolicyTest {
	@DisplayName("구입 금액과 수동 개수에 따라 랜덤 발급 개수가 계산되어야 한다")
	@ParameterizedTest
	@CsvSource({
		"1000, 0, 1",
		"1500, 0, 1",
		"2500, 1, 1",
		"2500, 2, 0"
	})
	void calculateRandomCountFromAmount_withAmountAndManualCount_returnsExpectedRandomCount(int amount, int manualCount, int expectedCount) {
		LottoPurchasePolicy policy = new LottoPurchasePolicy();

		int count = policy.calculateRandomCountFromAmount(amount, manualCount);

		assertThat(count).isEqualTo(expectedCount);
	}

	@DisplayName("랜덤 발급 계산 시 금액이 가격 미만이면 IllegalArgumentException이 발생해야 한다")
	@Test
	void calculateRandomCountFromAmount_withAmountLessThanPrice_throwsIllegalArgumentException() {
		LottoPurchasePolicy policy = new LottoPurchasePolicy();

		assertThatThrownBy(() -> policy.calculateRandomCountFromAmount(999, 0))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("랜덤 발급 계산 시 수동 개수가 음수면 IllegalArgumentException이 발생해야 한다")
	@Test
	void calculateRandomCountFromAmount_withNegativeManualCount_throwsIllegalArgumentException() {
		LottoPurchasePolicy policy = new LottoPurchasePolicy();

		assertThatThrownBy(() -> policy.calculateRandomCountFromAmount(1_000, -1))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("랜덤 발급 계산 시 수동 개수가 전체 발급 개수를 초과하면 IllegalArgumentException이 발생해야 한다")
	@Test
	void calculateRandomCountFromAmount_withManualCountExceedingTotal_throwsIllegalArgumentException() {
		LottoPurchasePolicy policy = new LottoPurchasePolicy();

		assertThatThrownBy(() -> policy.calculateRandomCountFromAmount(1_000, 2))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
