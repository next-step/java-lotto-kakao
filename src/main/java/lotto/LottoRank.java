package lotto;

import money.Money;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum LottoRank {
    FIRST(6, false, false, Money.won(2000000000)),
    SECOND(5, true, true, Money.won(30000000)),
    THIRD(5, true, false, Money.won(1500000)),
    FOURTH(4, false, false, Money.won(50000)),
    FIFTH(3, false, false, Money.won(5000)),
    LOSE(0, false, false, Money.won(0));


    private static final Map<String, LottoRank> RANK_MAP = createRankMap();

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

    private static Map<String, LottoRank> createRankMap() {
        Map<String, LottoRank> rankMap = new HashMap<>();
        for (LottoRank rank : values()) {
            setRankMap(rankMap, rank);
        }
        return Collections.unmodifiableMap(rankMap);
    }

    private static void setRankMap(Map<String, LottoRank> rankMap, LottoRank rank) {
        if (rank.isLose()) {
            return;
        }

        if (rank.bonusDependent) {
            rankMap.put(rankKey(rank.matchCount, rank.hasBonus), rank);
            return;
        }

        rankMap.put(rankKey(rank.matchCount, true), rank);
        rankMap.put(rankKey(rank.matchCount, false), rank);
    }

    public static LottoRank searchRank(int matchCount, boolean hasBonus) {
        String key = rankKey(matchCount, hasBonus);
        return RANK_MAP.getOrDefault(key, LottoRank.LOSE);
    }

    private static String rankKey(int matchCount, boolean hasBonus) {
        return matchCount + ":" + hasBonus;
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
