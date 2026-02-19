package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.model.Buyer;
import lotto.model.Lotto;
import lotto.model.Money;

public class BuyerTest {

	@Test
	void buyWithExactAmountTest() {
		Money money = new Money(14_000);
		Buyer buyer = Buyer.buyLotteries(money);
		List<Lotto> tickets = buyer.getTickets();
		assertThat(tickets.size()).isEqualTo(14);
	}
}
