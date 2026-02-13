package money;

import java.util.Objects;

public final class Money {
    private final long value;

    private Money(long value) {
        validate(value);
        this.value = value;
    }

    public static Money won(long value) {
        return new Money(value);
    }

    private void validate(long value) {
        if (value < 0L) {
            throw new IllegalArgumentException(
                    String.format("돈은 0 또는 양수여야 합니다.: %d", value)
            );
        }
    }

    public Money times(long multiplier) {
        if (multiplier < 0) {
            throw new IllegalArgumentException("곱하는 값은 0 또는 양수여야 합니다.: " + multiplier);
        }
        return Money.won(Math.multiplyExact(this.value, multiplier));
    }

    public double calculateMoneyRate(Money divisor) {
        if (divisor.value <= 0L) {
            throw new IllegalArgumentException("비교 금액은 0일 수 없습니다.");
        }
        return (double) this.value / divisor.value;
    }

    public Money plus(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("Money는 null일 수 없습니다.");
        }
        return Money.won(Math.addExact(this.value, other.value));
    }

    public Money minus(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("Money는 null일 수 없습니다.");
        }
        return Money.won(Math.subtractExact(this.value, other.value));
    }

    public long calculatePurchasableCount(long price) {
        return value / price;
    }

    public boolean isMultipleOf(long price) {
        return value % price == 0L;
    }

    public boolean isZero() {
        return value == 0L;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Money) obj;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + "원";
    }
}
