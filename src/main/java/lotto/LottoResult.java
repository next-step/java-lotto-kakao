package lotto;
import java.util.*;

public class LottoResult {
    private final Map<Rank, WinningCount> result;

    public LottoResult(List<Rank> ranks) {
        this.result = summarize(ranks);
    }

    private Map<Rank, WinningCount> summarize(List<Rank> ranks) {
        Map<Rank, WinningCount> summary = new EnumMap<>(Rank.class);
        Arrays.stream(Rank.values()).forEach(rank -> summary.put(rank, new WinningCount(0)));

        for (Rank rank : ranks) {
            summary.get(rank).addCount();
        }
        return summary;
    }

    public int getCount(Rank rank) {
        return result.get(rank).getCount();
    }

    public double calculateYield(Money purchaseMoney) {
        long totalPrize = Arrays.stream(Rank.values())
                .mapToLong(rank -> (long) rank.getWinningMoney() * result.get(rank).getCount())
                .sum();

        return YieldCalculator.calculate(totalPrize, purchaseMoney.getMoney());
    }
}