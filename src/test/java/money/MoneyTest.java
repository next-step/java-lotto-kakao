package money;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void createFailBelowRange() {
        assertThrows(IllegalArgumentException.class,
                () -> Money.won(-10));
    }

    @Test
    void calculatePurchasableCountSuccess() {
        Money money = Money.won(14000L);
        Money price = Money.won(1000L);
        long count = money.calculatePurchasableCount(price);
        assertThat(count).isEqualTo(14);
    }

    @Test
    void calculatePurchasableCountFailWhenPriceIsZero() {
        Money money = Money.won(14000L);
        Money price = Money.won(0L);
        assertThrows(IllegalArgumentException.class,
                () -> money.calculatePurchasableCount(price));
    }
}
