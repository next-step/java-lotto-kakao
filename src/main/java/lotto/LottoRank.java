package lotto;

import money.Money;

public enum LottoRank {
    FIRST(6, false, Money.won(2000000000)),
    SECOND(5, true, Money.won(30000000)),
    THIRD(5, false, Money.won(1500000)),
    FOURTH(4, false, Money.won(50000)),
    FIFTH(3, false, Money.won(5000)),
    LOSE(0, false, Money.won(0));

    public final Money prize;
    private final int matchCount;
    private final Boolean hasBonus;

    LottoRank(int matchCount, Boolean hasBonus, Money prize) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.prize = prize;
    }

    public static LottoRank searchRank(int count, boolean bonus) {
        for (LottoRank rank : values()) {
            if (rank.matches(count, bonus)) {
                return rank;
            }
        }
        return LOSE;
    }

    private boolean matches(int count, boolean bonus) {
        if (this == LOSE) {
            return false;
        }
        if (this.matchCount != count) {
            return false;
        }
        return this.matchCount != 5 || hasBonus == bonus;
    }
}
