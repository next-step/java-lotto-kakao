package lotto;

import java.util.*;

public class LottoResult {
    private final Map<Rank, Counter> result;

    private LottoResult(List<Rank> ranks) {
        this.result = summarize(ranks);
    }

    public static LottoResult from(List<Rank> ranks) {
        return new LottoResult(ranks);
    }

    @SafeVarargs
    public static LottoResult fromMany(List<Rank>... rankLists) {
        List<Rank> merged = new ArrayList<>();
        for (List<Rank> list : rankLists) {
            merged.addAll(list);
        }
        return new LottoResult(merged);
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
        Money totalPrize = Arrays.stream(Rank.values())
                .map(rank -> rank.winningMoney(toCount(rank)))
                .reduce(Money.zero(), Money::sum);
        if (purchaseMoney.money() == 0) {
            return 0;
        }

        double yield = (double) totalPrize.money() / purchaseMoney.money();
        return Math.floor(yield * 100) / 100.0;
    }
}