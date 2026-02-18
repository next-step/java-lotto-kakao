package lotto;

import money.Money;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchasePlanTest {
    @Test
    void createPurchasePlan() {
        PurchasePlan purchasePlan = PurchasePlan.from(
                Money.won(14000),
                List.of(
                        new Lotto(8, 21, 23, 41, 42, 43),
                        new Lotto(3, 5, 11, 16, 32, 38),
                        new Lotto(7, 11, 16, 35, 36, 44)
                )
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
                        List.of(
                                new Lotto(1, 2, 3, 4, 5, 6),
                                new Lotto(7, 8, 9, 10, 11, 12),
                                new Lotto(13, 14, 15, 16, 17, 18)
                        )
                ));
    }

    @Test
    void FailWhenMoneyIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> PurchasePlan.from(Money.won(0), List.of()));
    }
}
