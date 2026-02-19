package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoPurchaseTest {
	@Test
	@DisplayName("LottoPurchase는 수동/자동/총 구매 수량을 계산한다")
	void create_lotto_purchase() {
		LottoPurchase lottoPurchase = new LottoPurchase(new Money(14000), new Count(3));

		assertThat(lottoPurchase.totalLottoCount()).isEqualTo(new Count(14));
		assertThat(lottoPurchase.manualLottoCount()).isEqualTo(new Count(3));
		assertThat(lottoPurchase.autoLottoCount()).isEqualTo(new Count(11));
	}

	@Test
	@DisplayName("수동 구매 수량이 전체 수량을 초과하면 예외가 발생한다")
	void create_fail_when_manual_count_is_greater_than_total_count() {
		assertThatThrownBy(() -> new LottoPurchase(new Money(14000), new Count(15)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("수동 구매 수량이 전체 수량과 같으면 자동 구매 수량은 0이다")
	void auto_count_is_zero_when_all_tickets_are_manual() {
		LottoPurchase lottoPurchase = new LottoPurchase(new Money(2000), new Count(2));

		assertThat(lottoPurchase.autoLottoCount()).isEqualTo(new Count(0));
	}
}
