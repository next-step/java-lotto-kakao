package lotto;

import java.util.ArrayList;
import java.util.List;

public record LottoNumber(int value) implements Comparable<LottoNumber> {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final List<LottoNumber> ALL_NUMBERS = createAllNumbers();

    public LottoNumber {
        validate(value);
    }

    public static List<LottoNumber> allNumbers() {
        return new ArrayList<>(ALL_NUMBERS);
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

    private static List<LottoNumber> createAllNumbers() {
        List<LottoNumber> allNumbers = new ArrayList<>();
        for (int value = MIN; value <= MAX; value++) {
            allNumbers.add(new LottoNumber(value));
        }
        return List.copyOf(allNumbers);
    }
}
