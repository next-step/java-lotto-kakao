package lotto.domain;

public class LottoPurchasePolicy {
	private static final int LOTTO_PRICE = 1_000;

	public int calculateRandomCountFromAmount(int amount, int manualCount) {
		validateAmount(amount);
		validateCount(manualCount);
		int totalCount = amount / LOTTO_PRICE;
		if (manualCount > totalCount) {
			throw new IllegalArgumentException("수동 구매 수는 전체 구매 수를 초과할 수 없습니다.");
		}
		return totalCount - manualCount;
	}

	private void validateAmount(int amount) {
		if (amount < LOTTO_PRICE) {
			throw new IllegalArgumentException(String.format("구입 금액은 %s원 이상이어야 합니다.", LOTTO_PRICE));
		}
	}

	private void validateCount(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("구매 수는 0 이상이어야 합니다.");
		}
	}
}
