package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    MISS(0, 0, false);

    private final int countOfMatch;
    private final int winningMoney;
    private final boolean matchBonus;

    Rank(int countOfMatch, int winningMoney, boolean matchBonus) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
		this.matchBonus = matchBonus;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch > 6 || countOfMatch < 0){
            throw new IllegalArgumentException("맞춘 번호는 0 ~ 6 사이여야 합니다.");
        }

        return Arrays.stream(Rank.values())
            .filter(rank -> rank.isSatisfiedBy(countOfMatch, matchBonus))
            .findAny()
            .orElse(MISS);
    }

    private boolean isSatisfiedBy(int countOfMatch, boolean matchBonus) {
        return this.countOfMatch == countOfMatch && this.matchBonus == matchBonus;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }
}
