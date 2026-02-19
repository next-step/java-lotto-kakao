package lotto;

public final class Money {
    private final long money;
    private final static long LOTTO_UNIT = 1000;
    private final static Money ZERO = new Money(0);

    public Money(long money) {
        validateUnit(money);
        this.money = money;
    }

    public static Money zero() {
        return ZERO;
    }

    public Money sum(Money other) {
        if(this.money + other.money == 0){
            return zero();
        }
        return new Money(this.money + other.money);
    }

    public long money() {
        return money;
    }

    public int calculateLottoCount() {
        return (int) (money / LOTTO_UNIT);
    }

    private void validateUnit(long money) {
        if (money % LOTTO_UNIT != 0) {
            throw new IllegalArgumentException("천원 단위로만 입력이 가능합니다.");
        }
        if (money < 0) {
            throw new IllegalArgumentException("양수로만 입력이 가능합니다.");
        }
    }
}
