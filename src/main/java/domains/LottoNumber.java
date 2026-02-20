package domains;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public record LottoNumber(int number) implements Comparable<LottoNumber> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final List<LottoNumber> CACHE = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
            .mapToObj(LottoNumber::new)
            .toList();

    public LottoNumber {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1~45사이여야 합니다.");
        }
    }

    public static List<LottoNumber> values() {
        return new ArrayList<>(CACHE); // 방어적 복사 추천
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}