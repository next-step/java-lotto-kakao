package lotto.enums;

public enum LottoStatus {
    ZERO(0, 0),
    ONE(1, 0),
    TWO(2, 0),
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    SIX(6, 2000000000),
    SIX_BONUS(6, 30000000),
    ANSWER(6, 0);

    private final int count;
    private final long money;

    LottoStatus(int count, long money) {
        this.count = count;
        this.money = money;
    }

    public int getCount() {
        return count;
    }
    public long getMoney() {return money;}

    public static LottoStatus update(LottoStatus status) {
        int nextIndex = status.ordinal() + 1;
        LottoStatus[] values = LottoStatus.values();

        return values[nextIndex];
    }
}
