package lotto;

import lotto.model.PurchaseAmount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseAmountTest {
    @ParameterizedTest
    @ValueSource(longs = {-1, 0})
    void 구매_금액은_0_이하가_될_수_없다(long amount) {
        assertThatThrownBy(() -> new PurchaseAmount(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void decrease로_구매_금액을_차감할_수_있다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(10_000L);
        purchaseAmount.decrease(1_000L);
        assertThat(purchaseAmount.getPurchaseAmount()).isEqualTo(9_000L);
    }
}
