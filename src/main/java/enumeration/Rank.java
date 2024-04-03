package enumeration;

import domain.Lotto;
import domain.WinningLotto;

import java.util.Arrays;

import static enumeration.LottoCondition.LENGTH;
import static enumeration.LottoCondition.SUBTLE_CRITERIA;

public enum Rank {
    FIRST(1, 2_000_000_000),
    SECOND(2, 30_000_000),
    THIRD(3, 1_500_000),
    FORTH(4, 50_000),
    FIFTH(5, 5_000),
    NONE(0, 0),
    ;

    private final int rank;
    private final int prize;

    Rank(int rank, int prize) {
        this.rank = rank;
        this.prize = prize;
    }

    public static Rank of(Lotto lotto, WinningLotto winningLotto) {
        int matchCount = lotto.matchCount(winningLotto);
        boolean matchBonus = lotto.matchBonus(winningLotto);
        return Rank.of(Rank.getRank(matchCount, matchBonus));
    }

    public static Rank of(int rank) {
        return Arrays.stream(Rank.values())
                .filter(e -> e.rank == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("당첨을 판별할 수 없는 순위입니다."));
    }

    private static int getRank(final int matchCount, final boolean matchBonus) {
        if (matchCount == LENGTH.value()) {
            return 1;
        }
        if (matchCount == LENGTH.value() - 1 && matchBonus) {
            return 2;
        }
        if (matchCount >= LENGTH.value() - 3) {
            return LENGTH.value() - matchCount + SUBTLE_CRITERIA.value();
        }
        return 0;
    }

    public int rank() {
        return rank;
    }

    public long prize() {
        return prize;
    }
}
