package lotto;

import money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoShopTest {
    @Test
    void purchaseBundle() {
        long moneyAmount = 14000L;
        LottoBundle lottoBundle = LottoShop.purchaseBundle(Money.won(moneyAmount));
        assertThat(moneyAmount / LottoShop.PRICE).isEqualTo(lottoBundle.size());
    }

    @Test
    void purchaseBundleFail() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoShop.purchaseBundle(Money.won(1400)));
        assertThrows(IllegalArgumentException.class,
                () -> LottoShop.purchaseBundle(Money.won(0)));
    }
}
