package lotto;

import money.Money;

public final class PurchasePlan {
    private final int manualCount;
    private final long autoCount;

    private PurchasePlan(int manualCount, long autoCount) {
        this.manualCount = manualCount;
        this.autoCount = autoCount;
    }

    public static PurchasePlan from(Money money, int manualCount) {
        validateManualCount(manualCount);
        long totalCount = LottoPurchasePolicy.calculatePurchasableCount(money);

        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 수량은 전체 구매 가능 수량을 초과할 수 없습니다.");
        }

        long autoCount = totalCount - manualCount;
        return new PurchasePlan(manualCount, autoCount);
    }

    private static void validateManualCount(int manualCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException("수동 구매 수량은 음수일 수 없습니다.");
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public long getAutoCount() {
        return autoCount;
    }

    public long getTotalCount() {
        return getManualCount() + autoCount;
    }
}
