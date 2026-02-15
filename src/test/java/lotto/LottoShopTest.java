package lotto;

import money.Money;
import org.junit.jupiter.api.Test;
import purchase.LottoBundlePurchase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoShopTest {
    private static final Money MONEY_14500 = Money.won(14500L);

    @Test
    void createsBundleWithPurchasableCount() {
        LottoBundlePurchase purchase = LottoShop.purchaseBundle(MONEY_14500);
        assertThat(purchase.lottoBundle().size()).isEqualTo(14);
    }

    @Test
    void calculatePaid() {
        LottoBundlePurchase purchase = LottoShop.purchaseBundle(MONEY_14500);
        assertThat(purchase.paid()).isEqualTo(Money.won(14000L));
    }

    @Test
    void calculateChange() {
        LottoBundlePurchase purchase = LottoShop.purchaseBundle(MONEY_14500);
        assertThat(purchase.change()).isEqualTo(Money.won(500L));
    }

    @Test
    void purchaseBundleFailWhenAmountIsZero() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoShop.purchaseBundle(Money.won(0L)));
    }

    @Test
    void purchaseBundleFailWhenAmountIsLessThanPrice() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoShop.purchaseBundle(Money.won(999L)));
    }
}
