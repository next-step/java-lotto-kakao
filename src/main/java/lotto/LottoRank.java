package lotto;

import money.Money;

import java.util.HashMap;
import java.util.Map;

public enum LottoRank {
    FIRST(6, false, false, Money.won(2000000000)),
    SECOND(5, true, true, Money.won(30000000)),
    THIRD(5, true, false, Money.won(1500000)),
    FOURTH(4, false, false, Money.won(50000)),
    FIFTH(3, false, false, Money.won(5000)),
    LOSE(0, false, false, Money.won(0));


    private static final Map<String, LottoRank> RANK_MAP = new HashMap<>();

    static {
        for (LottoRank rank : values()) {
            setRankMap(rank);
        }
    }

    private final int matchCount;
    private final boolean bonusDependent;
    private final boolean hasBonus;
    private final Money prize;

    LottoRank(int matchCount, boolean bonusDependent, boolean hasBonus, Money prize) {
        this.matchCount = matchCount;
        this.bonusDependent = bonusDependent;
        this.hasBonus = hasBonus;
        this.prize = prize;
    }

    private static void setRankMap(LottoRank rank) {
        if (rank.isLose()) {
            return;
        }

        if (rank.bonusDependent) {
            RANK_MAP.put(rank.matchCount + ":" + rank.hasBonus, rank);
            return;
        }

        RANK_MAP.put(rank.matchCount + ":true", rank);
        RANK_MAP.put(rank.matchCount + ":false", rank);
    }

    public static LottoRank searchRank(int count, boolean bonus) {
        String key = count + ":" + bonus;
        return RANK_MAP.getOrDefault(key, LottoRank.LOSE);
    }

    public Money calculatePrize(int quantity) {
        return this.prize.times(quantity);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrizeAmount() {
        return prize.amount();
    }

    public boolean isSecond() {
        return this == LottoRank.SECOND;
    }

    public boolean isLose() {
        return this == LottoRank.LOSE;
    }
}
