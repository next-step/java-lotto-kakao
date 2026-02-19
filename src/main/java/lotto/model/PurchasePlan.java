package lotto.model;

public class PurchasePlan {
	private final int manualCount;
	private final int autoCount;

	private PurchasePlan(int manualCount, int autoCount) {
		this.manualCount = manualCount;
		this.autoCount = autoCount;
	}

	public static PurchasePlan of(Money money, int manualCount) {
		validateManualCount(money, manualCount);
		int autoCount = money.purchasableLottoCount() - manualCount;
		return new PurchasePlan(manualCount, autoCount);
	}

	private static void validateManualCount(Money money, int manualCount) {
		if (manualCount < 0) {
			throw new IllegalArgumentException("수동 구매 수량은 0 이상이어야 합니다.");
		}
		if (manualCount > money.purchasableLottoCount()) {
			throw new IllegalArgumentException("수동 구매 수량이 총 구매 가능 수량을 초과할 수 없습니다.");
		}
	}

	public int manualCount() {
		return manualCount;
	}

	public int autoCount() {
		return autoCount;
	}
}
