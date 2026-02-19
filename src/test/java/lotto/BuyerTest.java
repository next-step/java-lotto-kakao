package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.model.Buyer;
import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.Money;
import lotto.model.PurchasePlan;

public class BuyerTest {

	@Test
	void buyWithExactAmountTest() {
		Money money = new Money(14_000);
		PurchasePlan plan = PurchasePlan.of(money, 0);
		Buyer buyer = Buyer.buyLotteries(plan, new ArrayList<>());
		List<Lotto> tickets = buyer.getTickets();
		assertThat(tickets.size()).isEqualTo(14);
	}

	@Test
	void buyWithManualAndAutoLotteries() {
		Money money = new Money(14_000);
		PurchasePlan plan = PurchasePlan.of(money, 3);
		List<Lotto> manualTickets = List.of(
			createLotto(1, 2, 3, 4, 5, 6),
			createLotto(7, 8, 9, 10, 11, 12),
			createLotto(13, 14, 15, 16, 17, 18)
		);

		Buyer buyer = Buyer.buyLotteries(plan, manualTickets);

		assertThat(buyer.getTickets()).hasSize(14);
		assertThat(buyer.getTickets()).containsAll(manualTickets);
	}

	@Test
	void throwExceptionWhenManualCountDiffersFromPlan() {
		Money money = new Money(14_000);
		PurchasePlan plan = PurchasePlan.of(money, 3);
		List<Lotto> manualTickets = List.of(
			createLotto(1, 2, 3, 4, 5, 6),
			createLotto(7, 8, 9, 10, 11, 12)
		);

		assertThatThrownBy(() -> Buyer.buyLotteries(plan, manualTickets))
			.isInstanceOf(IllegalArgumentException.class);
	}

	private Lotto createLotto(int... values) {
		List<LottoNumber> numbers = Arrays.stream(values)
			.mapToObj(LottoNumber::new)
			.toList();
		return new Lotto(numbers);
	}
}
