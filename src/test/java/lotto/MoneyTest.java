package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
	@Test
	@DisplayName("구매 가능한 티켓 수량을 반환한다 (1,000의 배수)")
	public void getTicketCount_when_amount_is_multiple_of_1000(){
		Money money = new Money(22000);
		assertThat(money.toPurchaseCount()).isEqualTo(new Count(22));
	}

	@Test
	@DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다")
	public void cannot_create_money_when_amount_is_not_thousand_unit(){
		assertThatThrownBy(() -> new Money(22500))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
