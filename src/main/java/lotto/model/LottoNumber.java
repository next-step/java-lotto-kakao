package lotto.model;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int START_NUMBER = 1;
    public static final int END_NUMBER = 45;
    private final int num;

    public LottoNumber(int num) {
        if (num < START_NUMBER || num > END_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 "+START_NUMBER+"-"+END_NUMBER+" 사이 값이어야 합니다");
        }
        this.num = num;
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
