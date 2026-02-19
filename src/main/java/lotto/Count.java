package lotto;

import java.util.Objects;

public final class Count {
	private final int value;

	public Count(int value) {
		validateNonNegative(value);
		this.value = value;
	}

	public Count add(Count other) {
		return new Count(value + requireValue(other));
	}

	public Count subtract(Count other) {
		return new Count(value - requireValue(other));
	}

	public int value() {
		return value;
	}

	public boolean isGreaterThan(Count other) {
		return value > requireValue(other);
	}

	public boolean isZero() {
		return value == 0;
	}

	private int requireValue(Count other) {
		return Objects.requireNonNull(other).value;
	}

	private void validateNonNegative(int value) {
		if (value < 0) {
			throw new IllegalArgumentException("개수는 0 이상이어야 합니다.");
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof Count count)) {
			return false;
		}
		return value == count.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
