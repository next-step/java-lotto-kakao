package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoTotalResult {
    private final static int LOTTO_RESULT_DEFAULT = 0;

    private final Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
    private final int totalPrice;
    private final int totalProfit;

    public LottoTotalResult(LottoResults lottoResults) {
        this.totalPrice = lottoResults.getLottoResultsSize() * Lotto.getPrice();
        int profitSum = 0;
        initRankCounts();

        for (Rank rank : lottoResults.getLottoResultRanks()) {
            rankCounts.put(rank, rankCounts.get(rank) + 1);
            profitSum += rank.getWinningMoney();
        }

        this.totalProfit = profitSum;
    }

    private void initRankCounts() {
        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, LOTTO_RESULT_DEFAULT);
        }
    }

    public String getTotalResultString() {
        StringBuilder sb = new StringBuilder();

        for (Rank rank : getValidRanks()) {
            int count = rankCounts.getOrDefault(rank, LOTTO_RESULT_DEFAULT);
            sb.append(getRankString(rank, count));
        }

        return sb.toString();
    }

    private List<Rank> getValidRanks() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .toList();
    }

    private String getRankString(Rank rank, int count) {
        StringBuilder sb = new StringBuilder();

        sb.append(rank.getBallCount())
                .append("개 일치");
        if (rank.equals(Rank.SECOND)) {
            sb.append(", 보너스 볼 일치");
        }
        sb.append(" (")
                .append(rank.getWinningMoney())
                .append("원) - ")
                .append(count)
                .append("개\n");

        return sb.toString();
    }

    public double getProfit() {
        return (double) totalProfit / totalPrice;
    }
}
