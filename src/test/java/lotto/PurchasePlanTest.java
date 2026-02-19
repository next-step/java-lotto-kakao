package lotto;

import money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchasePlanTest {
    @Test
    void createPurchasePlan() {
        PurchasePlan purchasePlan = PurchasePlan.from(
                Money.won(14000),
                3
        );

        assertThat(purchasePlan.getManualCount()).isEqualTo(3);
        assertThat(purchasePlan.getAutoCount()).isEqualTo(11);
        assertThat(purchasePlan.getTotalCount()).isEqualTo(14);
    }

    @Test
    void FailManualCountExceeds() {
        assertThrows(IllegalArgumentException.class,
                () -> PurchasePlan.from(
                        Money.won(2000),
                        3
                ));
    }

    @Test
    void FailWhenMoneyIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> PurchasePlan.from(Money.won(0), 0));
    }

    @Test
    void failWhenManualCountIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> PurchasePlan.from(Money.won(1000), -1));
    }
}
