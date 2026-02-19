package lotto;

import money.Money;

final class LottoPurchasePolicy {
    private LottoPurchasePolicy() {
    }

    static long calculatePurchasableCount(Money money) {
        if (money == null) {
            throw new IllegalArgumentException("구매금액은 null일 수 없습니다.");
        }

        long count = money.calculatePurchasableCount(Lotto.PRICE);
        if (count <= 0L) {
            throw new IllegalArgumentException(
                    String.format("구매금액은 로또 가격 이상이어야 합니다. 로또 가격 : %d", Lotto.PRICE)
            );
        }
        return count;
    }
}
