package lotto.domain;

import java.util.Arrays;

public enum Rank {

    MISS(-1, false, 0),
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000);

    private final int ballCount;
    private final boolean matchBonus;
    private final int winningMoney;

    Rank(int ballCount, boolean matchBonus, int winningMoney) {
        this.ballCount = ballCount;
        this.matchBonus = matchBonus;
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
                .filter(rank -> rank != MISS)
                .filter(rank -> rank.matches(countOfMatch, matchBonus))
                .findFirst()
                .orElse(MISS);
    }

    private boolean matches(int countOfMatch, boolean matchBonus) {
        return this.ballCount == countOfMatch
                && this.matchBonus == matchBonus;
    }
}
