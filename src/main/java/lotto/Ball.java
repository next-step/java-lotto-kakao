package lotto;

import java.util.Objects;

public class Ball implements Comparable<Ball> {
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;
	private final int value;

	public Ball(int value) {
		validateRange(value);
		this.value = value;
	}

	private void validateRange(int value) {
		if (value < MIN_NUMBER || value > MAX_NUMBER) {
			throw new IllegalArgumentException("1부터 45 사이의 숫자만 입력 가능합니다.");
		}
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Ball ball = (Ball)o;
		return value == ball.value;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}

	@Override
	public int compareTo(Ball o) {
		return Integer.compare(this.value, o.getValue());
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
