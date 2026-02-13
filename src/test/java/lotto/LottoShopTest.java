package lotto;

import money.Money;
import org.junit.jupiter.api.Test;
import purchase.Purchase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoShopTest {
    @Test
    void purchaseBundle() {
        long moneyAmount = 14000L;
        long count = moneyAmount / LottoShop.PRICE;
        Money total = Money.won(moneyAmount);
        Money paid = Money.won(LottoShop.PRICE).times(count);
        Money change = total.minus(paid);

        Purchase<LottoBundle> lottoBundlePurchase = LottoShop.purchaseBundle(Money.won(moneyAmount));
        assertThat(count).isEqualTo(lottoBundlePurchase.item().size());
        assertThat(paid).isEqualTo(lottoBundlePurchase.paid());
        assertThat(change).isEqualTo(lottoBundlePurchase.change());
    }

    @Test
    void purchaseBundleFail() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoShop.purchaseBundle(Money.won(1400)));
        assertThrows(IllegalArgumentException.class,
                () -> LottoShop.purchaseBundle(Money.won(0)));
    }
}
