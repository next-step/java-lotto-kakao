package lotto;

import money.Money;
import purchase.LottoBundlePurchase;

import java.util.List;

public class LottoShop {
    public static final Money PRICE = Money.won(1000L);

    public static LottoBundlePurchase purchaseBundle(Money purchaseAmount, LottoForm lottoForm) {
        long totalCount = purchaseAmount.calculatePurchasableCount(PRICE);
        int manualCount = lottoForm.size();
        validatePurchaseCount(totalCount, manualCount);

        LottosGenerator generator = new CompositeLottosGenerator(
                new ManualLottosGenerator(lottoForm),
                new AutoLottosGenerator(totalCount - manualCount)
        );

        List<Lotto> lottos = generator.generate();
        Money paid = PRICE.times(lottos.size());
        Money change = purchaseAmount.minus(paid);
        return new LottoBundlePurchase(new LottoBundle(lottos), paid, change);
    }

    private static void validatePurchaseCount(long totalCount, int manualCount) {
        if (totalCount < 1) {
            throw new IllegalArgumentException("구입금액이 부족합니다.");
        }
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 로또 개수가 구매 가능한 개수를 초과했습니다.");
        }
    }
}
