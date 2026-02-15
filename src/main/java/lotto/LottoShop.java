package lotto;

import money.Money;
import purchase.Purchase;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoShop {
    public static final Money PRICE = Money.won(1000L);

    public static Purchase<LottoBundle> purchaseBundle(Money purchaseAmount) {
        long count = purchaseAmount.calculatePurchasableCount(PRICE);
        if (count < 1) {
            throw new IllegalArgumentException("구입금액이 부족합니다.");
        }
        Money paid = PRICE.times(count);
        Money change = purchaseAmount.minus(paid);
        List<Lotto> lottos = Stream.generate(Lotto::random)
                .limit(count)
                .collect(Collectors.toList());
        return new Purchase<>(new LottoBundle(lottos), paid, change);
    }
}
