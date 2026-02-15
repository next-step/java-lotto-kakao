package lotto;

import money.Money;

import java.util.Map;

public class LottoBundleResult {
    private final Map<LottoRank, Integer> lottoRankIntegerMap;

    LottoBundleResult(Map<LottoRank, Integer> lottoRankIntegerMap) {
        this.lottoRankIntegerMap = lottoRankIntegerMap;
    }

    public int getRankCount(LottoRank lottoRank) {
        return lottoRankIntegerMap.getOrDefault(lottoRank, 0);
    }

    public double calculateProfitRate(Money totalFee) {
        Money totalPrize = Money.won(0L);

        for (LottoRank rank : LottoRank.values()) {
            totalPrize = totalPrize.plus(rank.calculatePrize(getRankCount(rank)));
        }

        return totalPrize.calculateMoneyRate(totalFee);
    }
}
