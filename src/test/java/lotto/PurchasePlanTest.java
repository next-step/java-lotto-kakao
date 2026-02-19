package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import lotto.model.Money;
import lotto.model.PurchasePlan;

public class PurchasePlanTest {

	@Test
	void calculateAutoAndManualCount() {
		Money money = new Money(14_000);
		PurchasePlan plan = PurchasePlan.of(money, 3);

		assertThat(plan.manualCount()).isEqualTo(3);
		assertThat(plan.autoCount()).isEqualTo(11);
	}

	@Test
	void throwExceptionWhenManualCountIsNegative() {
		Money money = new Money(14_000);

		assertThatThrownBy(() -> PurchasePlan.of(money, -1))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void throwExceptionWhenManualCountExceedsPurchasableCount() {
		Money money = new Money(14_000);

		assertThatThrownBy(() -> PurchasePlan.of(money, 15))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
