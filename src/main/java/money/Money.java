package money;

public record Money(long value) {
    public Money {
        validate(value);
    }

    private void validate(long value) {
        if (value <= 0L) {
            throw new IllegalArgumentException(
                    String.format("구입금액은 양수여야 합니다.: %d", value)
            );
        }
    }
}
