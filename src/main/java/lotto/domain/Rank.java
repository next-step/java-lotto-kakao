package lotto.domain;

import lotto.exception.LottoErrorCode;
import lotto.exception.LottoException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Rank {

    MISS(List.of(0, 1, 2), 0),
    FIFTH(List.of(3), 5_000),
    FOURTH(List.of(4), 50_000),
    THIRD(List.of(5), 1_500_000),
    SECOND(List.of(5), 30_000_000),
    FIRST(List.of(6), 2_000_000_000);

    private final List<Integer> ballCount;
    private final int winningMoney;

    Rank(List<Integer> ballCount, int winningMoney) {
        this.ballCount = ballCount;
        this.winningMoney = winningMoney;
    }

    public int getBallCount() {
        return Collections.max(ballCount);
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch == 5) {
            return matchBonus ? SECOND : THIRD;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matches(countOfMatch))
                .findFirst()
                .orElseThrow(() -> new LottoException(LottoErrorCode.INVALID_LOTTO_NUMBER_COUNT));
    }

    private boolean matches(int countOfMatch) {
        return this.ballCount.contains(countOfMatch);
    }

    public boolean isValidRank() {
        return this != MISS;
    }
}
