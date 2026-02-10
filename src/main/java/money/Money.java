package money;

import java.util.Objects;

public final class Money {
    private final long value;

    public Money(long value) {
        validate(value);
        this.value = value;
    }

    private void validate(long value) {
        if (value <= 0L) {
            throw new IllegalArgumentException(
                    String.format("구입금액은 양수여야 합니다.: %d", value)
            );
        }
    }

    public long calculatePurchasableCount(long price) {
        return value / price;
    }

    public boolean isMultipleOf(long price) {
        return value % price == 0;
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
}
