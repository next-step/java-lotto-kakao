package lotto;

import money.Money;
import org.junit.jupiter.api.Test;
import purchase.LottoBundlePurchase;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoShopTest {
    private static final Money MONEY_14500 = Money.won(14500L);

    @Test
    void containsMarkedLottoInPurchasedLottoBundle() {
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6);
        LottoForm lottoForm = new LottoForm();
        lottoForm.mark(numbers1);

        LottoBundlePurchase purchase = LottoShop.purchaseBundle(MONEY_14500, lottoForm);
        assertThat(purchase.lottoBundle().contains(new Lotto(numbers1))).isEqualTo(true);
        assertThat(purchase.lottoBundle().contains(new Lotto(List.of(1, 2, 3, 4, 5, 7)))).isEqualTo(false);
    }

    @Test
    void createsBundleWithPurchasableCount() {
        LottoBundlePurchase purchase = LottoShop.purchaseBundle(MONEY_14500);
        assertThat(purchase.lottoBundle().size()).isEqualTo(14);
    }

    @Test
    void createsBundleWithManualLottoAndPurchasableCount() {
        LottoForm lottoForm = new LottoForm();
        lottoForm.mark(List.of(1, 2, 3, 4, 5, 6));
        lottoForm.mark(List.of(11, 12, 13, 14, 15, 16));

        LottoBundlePurchase purchase = LottoShop.purchaseBundle(MONEY_14500, lottoForm);
        assertThat(purchase.lottoBundle().size()).isEqualTo(14);
    }

    @Test
    void calculatePaid() {
        LottoForm lottoForm = new LottoForm();
        lottoForm.mark(List.of(1, 2, 3, 4, 5, 6));
        lottoForm.mark(List.of(11, 12, 13, 14, 15, 16));

        LottoBundlePurchase purchase = LottoShop.purchaseBundle(MONEY_14500, lottoForm);
        assertThat(purchase.paid()).isEqualTo(Money.won(14000L));
    }

    @Test
    void calculateChange() {
        LottoForm lottoForm = new LottoForm();
        lottoForm.mark(List.of(1, 2, 3, 4, 5, 6));
        lottoForm.mark(List.of(11, 12, 13, 14, 15, 16));

        LottoBundlePurchase purchase = LottoShop.purchaseBundle(MONEY_14500, lottoForm);
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

    @Test
    void purchaseBundleFailWhenManualAmountIsMoreThanPrice() {
        LottoForm lottoForm = new LottoForm();
        lottoForm.mark(List.of(1, 2, 3, 4, 5, 6));
        lottoForm.mark(List.of(11, 12, 13, 14, 15, 16));

        assertThrows(IllegalArgumentException.class,
                () -> LottoShop.purchaseBundle(Money.won(1500L), lottoForm));
    }
}
