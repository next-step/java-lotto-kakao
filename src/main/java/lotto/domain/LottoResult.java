package lotto.domain;


import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoResult {

    private final Map<Prize, Long> result;

    public LottoResult(Map<Prize, Long> result) {
        this.result = result;
    }

    public LottoResult(WinningLotto winningLotto, List<LottoTicket> tickets) {
        this(aggregateResult(winningLotto, tickets));
    }

    public double getProfitRate(LottoPurchaseBudget budget) {
        long profit = result.keySet().stream()
                .mapToLong(prize -> prize.totalReward(result.get(prize)))
                .sum();

        return budget.calculateProfitRate(profit);
    }

    public Map<Prize, Long> getResult() {
        return Map.copyOf(result);
    }

    private static Map<Prize, Long> aggregateResult(WinningLotto winningLotto, List<LottoTicket> tickets) {
        return tickets.stream()
                .map(winningLotto::calculatePrize)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
