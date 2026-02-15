package lotto;

public final class Money {
    private final long money;
    private static final long LOTTO_UNIT = 1000;


    public Money(long money) {
        validateUnit(money);
        this.money = money;
    }

    public long money() {
        return money;
    }

    public int calculateLottoCount(){
        return (int) (money / LOTTO_UNIT);
    }

    private void validateUnit(long money) {
        if (money % LOTTO_UNIT != 0){
            throw new IllegalArgumentException("천원 단위로만 입력이 가능합니다.");
        }
        if (money < 0){
            throw new IllegalArgumentException("양수로만 입력이 가능합니다.");
        }
    }
}
