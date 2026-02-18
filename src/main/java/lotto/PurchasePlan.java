package lotto;

import money.Money;

import java.util.List;

public final class PurchasePlan {
    private final List<Lotto> manualLottos;
    private final long autoCount;

    private PurchasePlan(List<Lotto> manualLottos, long autoCount) {
        this.manualLottos = manualLottos;
        this.autoCount = autoCount;
    }

    public static PurchasePlan from(Money money, List<Lotto> manualLottos) {
        validateManualLottos(manualLottos);
        long totalCount = LottoPurchasePolicy.calculatePurchasableCount(money);

        int manualCount = manualLottos.size();
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 수량은 전체 구매 가능 수량을 초과할 수 없습니다.");
        }

        long autoCount = totalCount - manualCount;
        return new PurchasePlan(List.copyOf(manualLottos), autoCount);
    }

    private static void validateManualLottos(List<Lotto> manualLottos) {
        if (manualLottos == null) {
            throw new IllegalArgumentException("수동 로또 목록에 null이 포함될 수 없습니다.");
        }
        for (Lotto manualLotto : manualLottos) {
            if (manualLotto == null) {
                throw new IllegalArgumentException("수동 로또 목록에 null이 포함될 수 없습니다.");
            }
        }
    }

    public List<Lotto> getManualLottos() {
        return List.copyOf(manualLottos);
    }

    public int getManualCount() {
        return manualLottos.size();
    }

    public long getAutoCount() {
        return autoCount;
    }

    public long getTotalCount() {
        return getManualCount() + autoCount;
    }
}
