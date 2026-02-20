package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int START_NUMBER = 1;
    public static final int END_NUMBER = 45;
    private static final Map<Integer, LottoNumber> pool = new HashMap<>();

    static {
        for (int i = START_NUMBER; i <= END_NUMBER; i++) {
            pool.put(i, new LottoNumber(i));
        }
    }

    private final int num;

    private LottoNumber(int num) {
        this.num = num;
    }

    public static LottoNumber of(int num) {
        if (num < START_NUMBER || num > END_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 " + START_NUMBER + "-" + END_NUMBER + " 사이 값이어야 합니다");
        }
        return pool.get(num);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LottoNumber)) return false;
        LottoNumber number = (LottoNumber) obj;
        return this.num == number.num;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(num);
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.num, other.num);
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }

}
