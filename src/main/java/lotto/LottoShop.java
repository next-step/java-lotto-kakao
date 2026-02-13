package lotto;

import money.Money;
import purchase.Purchase;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {
    public static final long PRICE = 1000L;

    public static Purchase<LottoBundle> purchaseBundle(Money purchaseAmount) {
        long count = calculatePurchasableCount(purchaseAmount);
        Money paid = Money.won(PRICE).times(count);
        Money change = purchaseAmount.minus(paid);
        List<Lotto> lottos = new ArrayList<>();
        for (long c = 0; c < count; c++) {
            lottos.add(Lotto.random());
        }
        return new Purchase<>(new LottoBundle(lottos), paid, change);
    }

    private static long calculatePurchasableCount(Money money) {
        if (money.isZero()) {
            throw new IllegalArgumentException("구매금액은 0이면 안됩니다.");
        }
        if (!money.isMultipleOf(PRICE)) {
            throw new IllegalArgumentException(String.format("구매금액은 로또 가격의 배수여야 합니다. 로또 가격 : %d", PRICE));
        }
        return money.calculatePurchasableCount(PRICE);
    }
}
