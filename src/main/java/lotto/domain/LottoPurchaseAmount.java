package lotto.domain;

public class LottoPurchaseAmount {
	private final int manualCount;
	private final int autoCount;

	public LottoPurchaseAmount(Money money, int manualCount) {
		int totalCount = money.calculateLottoCount();
		validate(totalCount, manualCount);

		this.manualCount = manualCount;
		this.autoCount = totalCount - manualCount;
	}

	private void validate(int totalCount, int manualCount) {
		if (manualCount < 0) {
			throw new IllegalArgumentException("수량은 0보다 작을 수 없습니다.");
		}
		if (manualCount > totalCount) {
			throw new IllegalArgumentException("구입 금액을 초과하여 수동 구매할 수 없습니다.");
		}
	}

	public int getManualCount() {
		return manualCount;
	}

	public int getAutoCount() {
		return autoCount;
	}
}