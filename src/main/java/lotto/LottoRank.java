package lotto;

public enum LottoRank {

    FIRST(6, false),
    SECOND(5, true),
    THIRD(5, false),
    FOURTH(4, false),
    FIFTH(3, false),
    LOSE(0, false);

    private final int matchCount;
    private final Boolean hasBonus;


    LottoRank(int matchCount, Boolean hasBonus) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
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
