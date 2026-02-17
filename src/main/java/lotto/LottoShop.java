package lotto;

import money.Money;
import purchase.LottoBundlePurchase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LottoShop {
    public static final Money PRICE = Money.won(1000L);

    public static LottoBundlePurchase purchaseBundle(Money purchaseAmount) {
        return purchaseBundle(purchaseAmount, new LottoForm());
    }

    public static LottoBundlePurchase purchaseBundle(Money purchaseAmount, LottoForm lottoForm) {
        long totalCount = purchaseAmount.calculatePurchasableCount(PRICE);
        if (totalCount < 1) {
            throw new IllegalArgumentException("구입금액이 부족합니다.");
        }

        Money paid = PRICE.times(totalCount);
        Money change = purchaseAmount.minus(paid);

        return new LottoBundlePurchase(new LottoBundle(createAllLottos(totalCount, lottoForm)), paid, change);
    }

    private static List<Lotto> createAllLottos(long totalCount, LottoForm lottoForm) {
        List<Lotto> manual = lottoForm.manualLottos();
        int manualCount = manual.size();
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 로또 개수가 구매 가능한 개수를 초과했습니다.");
        }

        long autoCount = totalCount - manualCount;
        List<Lotto> auto = Stream.generate(Lotto::random)
                .limit(autoCount)
                .toList();
        List<Lotto> all = new ArrayList<>(manual);
        all.addAll(auto);
        return all;
    }
}
