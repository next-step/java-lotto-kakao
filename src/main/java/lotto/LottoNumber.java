package lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record LottoNumber(int value) implements Comparable<LottoNumber> {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final List<LottoNumber> ALL_NUMBERS = createAllNumbers();
    private static final Map<Integer, LottoNumber> CACHE = createCache();

    public LottoNumber {
        validateRange(value);
    }

    public static LottoNumber from(int value) {
        validateRange(value);
        return CACHE.get(value);
    }

    public static List<LottoNumber> allNumbers() {
        return ALL_NUMBERS;
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.value, other.value);
    }

    private static void validateRange(int value) {
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

    private static Map<Integer, LottoNumber> createCache() {
        Map<Integer, LottoNumber> cache = new HashMap<>();
        for (LottoNumber lottoNumber : ALL_NUMBERS) {
            cache.put(lottoNumber.value(), lottoNumber);
        }
        return Map.copyOf(cache);
    }
}
