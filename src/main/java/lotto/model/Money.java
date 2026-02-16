package lotto.model;

public record Money(int amount) {

	public Money {
		if (amount < 0) {
			throw new IllegalArgumentException("금액은 음수가 될 수 없습니다.");
		}
	}

	public Money plus(Money other) {
		return new Money(this.amount + other.amount);
	}

	public Money minus(Money other) {
		int result = this.amount - other.amount;
		if (result < 0) {
			throw new IllegalArgumentException("금액은 0 미만이 될 수 없습니다.");
		}
		return new Money(result);
	}

	public Money multiply(int multiplier) {
		if (multiplier < 0) {
			throw new IllegalArgumentException("배수는 0 이상이어야 합니다.");
		}
		return new Money(this.amount * multiplier);
	}

	public int divideBy(Money divisor) {
		if (divisor.amount <= 0) {
			throw new IllegalArgumentException("나누는 금액은 0보다 커야 합니다.");
		}
		return this.amount / divisor.amount;
	}

	public boolean isLessThan(Money other) {
		return this.amount < other.amount;
	}

	public boolean isGreaterThan(Money other){
		return this.amount > other.amount;
	}
}
