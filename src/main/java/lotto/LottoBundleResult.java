package lotto;

import money.Money;

import java.util.Map;

public class LottoBundleResult {
    private final Map<LottoRank, Integer> lottoRankIntegerMap;
    private final Money totalFee;

    public LottoBundleResult(Map<LottoRank, Integer> lottoRankIntegerMap, Money totalFee) {
        this.lottoRankIntegerMap = lottoRankIntegerMap;
        this.totalFee = totalFee;
    }

    public int getRankCount(LottoRank lottoRank) {
        return lottoRankIntegerMap.getOrDefault(lottoRank, 0);
    }

    public double calculateProfitRate() {
        Money totalPrize = Money.won(0L);

        for (LottoRank rank : LottoRank.values()) {
            totalPrize = totalPrize.plus(rank.calculatePrize(getRankCount(rank)));
        }

        return totalPrize.calculateMoneyRate(totalFee);
    }
}
