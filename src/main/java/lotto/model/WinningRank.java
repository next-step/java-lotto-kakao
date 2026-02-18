package lotto.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum WinningRank {

    NONE(0, 1000, 0, 0, 1, 2),
    FIFTH(5000, 5, 0, 3),
    FOURTH(50000, 4, 0, 4),
    THIRD(1_500_000, 3, 0, 5),
    SECOND(30_000_000, 2, 1, 5),
    FIRST(2_000_000_000, 1, 0, 6);

    public final Money winningPrice;
    public final int rank;
    private final List<Integer> matchCounts;
    private final int bounceCount;

    WinningRank(int winningPrice, int rank, int bounceCount, Integer... matchCounts) {
        this.winningPrice = new Money(winningPrice);
        this.rank = rank;
        this.bounceCount = bounceCount;
        this.matchCounts = Arrays.asList(matchCounts);
    }

    public boolean isSatisfied(int matchCount, int bounceCount) {
        return matchCounts.contains(matchCount)
                && bounceCount >= this.bounceCount;
    }

    public static WinningRank getRank(int matchCount, int bounceCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.isSatisfied(matchCount, bounceCount))
                .min(Comparator.comparingInt(r -> r.rank))
                .orElseThrow(() -> new RuntimeException("유효하지 않은 당첨 개수입니다: " + matchCount));
    }

    public int getMatchCount() {
        return matchCounts.getFirst();
    }

    public int getBounceCount() {
        return bounceCount;
    }

    public static List<WinningRank> getValidRanks() {
        return Arrays.stream(values())
                .filter(rank -> !rank.equals(NONE))
                .sorted(Comparator.comparingInt(r -> -r.rank))
                .collect(Collectors.toList());
    }
}
