package lotto;

public record LottoNumber(int value) implements Comparable<LottoNumber> {
    private static final int MIN = 1;
    private static final int MAX = 45;

    public LottoNumber {
        validate(value);
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.value, other.value);
    }

    private void validate(int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d부터 %d 사이여야 합니다.: %d", MIN, MAX, value)
            );
        }
    }
}
