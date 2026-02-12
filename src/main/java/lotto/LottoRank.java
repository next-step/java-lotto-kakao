package lotto;

import money.Money;

import java.util.ArrayList;
import java.util.List;

public enum LottoRank {
    FIRST(6, false, Money.won(2000000000)),
    SECOND(5, true, Money.won(30000000)),
    THIRD(5, false, Money.won(1500000)),
    FOURTH(4, false, Money.won(50000)),
    FIFTH(3, false, Money.won(5000)),
    LOSE(0, false, Money.won(0));

    private final Money prize;
    private final int matchCount;
    private final boolean hasBonus;
    private static final List<LottoRank> RANK_BY_MATCH_COUNT = new ArrayList<>(
            List.of(LOSE, LOSE, LOSE, FIFTH, FOURTH, THIRD, FIRST)
    );

    LottoRank(int matchCount, boolean hasBonus, Money prize) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.prize = prize;
    }

    public Money getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static LottoRank searchRank(int count, boolean bonus) {
        if (isSecondRankCondition(count, bonus)) {
            return SECOND;
        }
        if (isOutOfRange(count)) {
            return LOSE;
        }
        return RANK_BY_MATCH_COUNT.get(count);
    }

    private static boolean isSecondRankCondition(int count, boolean bonus) {
        return count == 5 && bonus;
    }

    private static boolean isOutOfRange(int count) {
        return count < 0 || count >= RANK_BY_MATCH_COUNT.size();
    }

    public boolean isSecond() {
        return this == SECOND;
    }
}
