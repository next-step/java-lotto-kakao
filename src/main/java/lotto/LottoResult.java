package lotto;

import java.util.Map;

public class LottoResult {

    private Map<LottoStatus, Integer> statuses;
    private long profit;
    private double profitRate;

    public LottoResult(Map<LottoStatus, Integer> map, long profit, double profitRate) {
        this.statuses = map;
        this.profit = profit;
        this.profitRate = profitRate;
    }

    public Map<LottoStatus, Integer> getStatuses() {
        return statuses;
    }

    public long getProfit() {
        return profit;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
