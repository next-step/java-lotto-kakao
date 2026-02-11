package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MoneyTest {
	@Test
	void 구입_금액은_1000으로_나눠떨어져야한다() {
		assertThatIllegalArgumentException().isThrownBy(() -> new Money(1001));
	}

	@Test
	void 구입금액은_1000으로_나눈_몫만큼의_개수로_로또를_구입해야_한다() {
		long price = 10000;
		Money money = new Money(price);
		assertThat(money.getTicketCount()).isEqualTo(price / 1000L);
	}
}
