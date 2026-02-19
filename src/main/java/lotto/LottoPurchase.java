package lotto;

import java.util.Objects;

public class LottoPurchase {
	private final Money purchaseMoney;
	private final Count totalLottoCount;
	private final Count manualLottoCount;
	private final Count autoLottoCount;

	public LottoPurchase(Money purchaseMoney, Count manualLottoCount) {
		this.purchaseMoney = Objects.requireNonNull(purchaseMoney);
		this.totalLottoCount = purchaseMoney.toPurchaseCount();
		this.manualLottoCount = Objects.requireNonNull(manualLottoCount);
		validateManualLottoCount();
		this.autoLottoCount = totalLottoCount.subtract(manualLottoCount);
	}

	public Money purchaseMoney() {
		return purchaseMoney;
	}

	public Count totalLottoCount() {
		return totalLottoCount;
	}

	public Count manualLottoCount() {
		return manualLottoCount;
	}

	public Count autoLottoCount() {
		return autoLottoCount;
	}

	private void validateManualLottoCount() {
		if (manualLottoCount.isGreaterThan(totalLottoCount)) {
			throw new IllegalArgumentException("수동 구매 수량은 전체 구매 수량을 초과할 수 없습니다.");
		}
	}
}
