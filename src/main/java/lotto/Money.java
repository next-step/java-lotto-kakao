package lotto;

public class Money {
	private static final int LOTTO_PRICE = 1000;
	private final int value;

	public Money(int value) {
		validateAmount(value);
		this.value = value;
	}

	public Count toPurchaseCount() {
		return new Count(value / LOTTO_PRICE);
	}

	public int getValue() {
		return value;
	}

	private void validateAmount(int value) {
		validateMinimumAmount(value);
		validateAmountUnit(value);
	}

	private void validateMinimumAmount(int value) {
		if (value < LOTTO_PRICE) {
			throw new IllegalArgumentException("구입 금액은 1000원 이상이어야 합니다.");
		}
	}

	private void validateAmountUnit(int value) {
		if (value % LOTTO_PRICE != 0) {
			throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Money money)) return false;
		return value == money.value;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(value);
	}
}
