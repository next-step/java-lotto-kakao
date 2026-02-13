package lotto;

import money.Money;
import purchase.Purchase;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {
    public static final long PRICE = 1000L;

    public static Purchase<LottoBundle> purchaseBundle(Money purchaseAmount) {
        long count = purchaseAmount.calculatePurchasableCount(PRICE);
        if (count < 1) {
            throw new IllegalArgumentException("구입금액이 부족합니다.");
        }
        Money paid = Money.won(PRICE).times(count);
        Money change = purchaseAmount.minus(paid);
        List<Lotto> lottos = new ArrayList<>();
        for (long c = 0; c < count; c++) {
            lottos.add(Lotto.random());
        }
        return new Purchase<>(new LottoBundle(lottos), paid, change);
    }
}
