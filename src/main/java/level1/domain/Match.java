package level1.domain;

import java.util.Arrays;

public enum Match {
    NONE(
            0, 0, ""
    ),
    THREE(
            3, 5_000, "3개 일치 (5000원)"
    ),
    FOUR(
            4, 50_000, "4개 일치 (50000원)"
    ),
    FIVE(
            5, 1_500_000, "5개 일치 (1500000원)"
    ),
    FIVE_WITH_BONUS(
            5, 30_000_000, "5개 일치, 보너스 볼 일치(30000000원)"
    ),
    SIX(
            6, 2_000_000_000, "6개 일치 (2000000000원)"
    );

    private final int matchCount;
    private final int prize;
    private final String description;

    Match(int matchCount, int prize, String description) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.description = description;
    }

    public static Match matchOf(long matchCount, boolean bonusBallMatch) {

        if (matchCount == FIVE.matchCount) {
            return findFiveMatch(bonusBallMatch);
        }

        return Arrays.stream(values())
                .filter(m -> m.matchCount == matchCount)
                .findFirst()
                .orElse(NONE);
    }

    private static Match findFiveMatch(boolean bonusBallMatch) {
        if (bonusBallMatch) {
            return FIVE_WITH_BONUS;
        }
        return FIVE;
    }

    public int getPrize() {
        return prize;
    }

    public String getDescription() {
        return description;
    }
}