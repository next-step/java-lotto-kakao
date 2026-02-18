package lotto;

import money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoPurchasePolicyTest {
    @Test
    void calculatePurchasableCount() {
        long count = LottoPurchasePolicy.calculatePurchasableCount(Money.won(5000));
        assertThat(count).isEqualTo(5L);
    }

    @Test
    void calculatePurchasableCountFailWhenMoneyIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoPurchasePolicy.calculatePurchasableCount(null));
    }

    @Test
    void calculatePurchasableCountFailWhenMoneyIsZero() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoPurchasePolicy.calculatePurchasableCount(Money.won(0)));
    }
}
