package lotto;

import java.util.*;

public class LottoResult {
    private final Map<Rank, Counter> result;

    public LottoResult(List<Rank> ranks) {
        this.result = summarize(ranks);
    }

    private Map<Rank, Counter> summarize(List<Rank> ranks) {
        Map<Rank, Counter> summary = new EnumMap<>(Rank.class);
        Arrays.stream(Rank.values()).forEach(rank -> summary.put(rank, new Counter(0)));

        for (Rank rank : ranks) {
            summary.get(rank).addCount();
        }
        return summary;
    }

    public int toCount(Rank rank) {
        return result.get(rank).count();
    }

    public double calculateYield(Money purchaseMoney) {
        long totalPrize = Arrays.stream(Rank.values())
                .mapToLong(rank -> (long) rank.toWinningMoney() * toCount(rank))
                .sum();

        return YieldCalculator.calculate(totalPrize, purchaseMoney.money());
    }
}