package lotto;

public final class Money {
    private final long money;

    public Money(long money) {
        validateUnit(money);
        this.money = money;
    }

    public long getMoney() {
        return money;
    }

    public int calculateLottoCount(){
        return (int) money / 1000;
    }

    private void validateUnit(long money) {
        if (money % 1000 != 0){
            throw new IllegalArgumentException("천원 단위로만 입력이 가능합니다.");
        }
    }
}
