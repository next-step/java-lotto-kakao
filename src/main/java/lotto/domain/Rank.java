package lotto.domain;

import java.util.Arrays;

public enum Rank {

    MISS(0, 0, 0),
    FIFTH(3, 5, 5_000),
    FOURTH(4, 4, 50_000),
    THIRD(5, 3, 1_500_000),
    SECOND(5, 2, 30_000_000),
    FIRST(6, 1, 2_000_000_000);

    private final int ballCount;
    private final int ranking;
    private final int winningMoney;

    Rank(int ballCount, int ranking, int winningMoney) {
        this.ballCount = ballCount;
        this.ranking = ranking;
        this.winningMoney = winningMoney;
    }

    public int getBallCount() {
        return ballCount;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matches(countOfMatch, matchBonus))
                .findFirst()
                .orElse(MISS);
    }

    private boolean matches(int countOfMatch, boolean matchBonus) {
        if (this == SECOND) {
            return countOfMatch == 5 && matchBonus;
        }
        if (this == THIRD) {
            return countOfMatch == 5 && !matchBonus;
        }
        return this.ballCount == countOfMatch;
    }
}
