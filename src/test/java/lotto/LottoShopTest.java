package lotto;

import money.Money;
import org.junit.jupiter.api.Test;
import purchase.Purchase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoShopTest {
    @Test
    void purchaseBundle() {
        Money total = Money.won(14500L);
        long count = total.calculatePurchasableCount(LottoShop.PRICE);
        Money paid = LottoShop.PRICE.times(count);
        Money change = total.minus(paid);

        Purchase<LottoBundle> lottoBundlePurchase = LottoShop.purchaseBundle(total);
        assertThat(count).isEqualTo(lottoBundlePurchase.item().size());
        assertThat(paid).isEqualTo(lottoBundlePurchase.paid());
        assertThat(change).isEqualTo(lottoBundlePurchase.change());
    }

    @Test
    void purchaseBundleFail() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoShop.purchaseBundle(Money.won(0)));
        assertThrows(IllegalArgumentException.class,
                () -> LottoShop.purchaseBundle(Money.won(999)));
    }
}
