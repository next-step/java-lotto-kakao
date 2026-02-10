package lotto;

import java.util.Map;

public class LottoBundleResult {
    private final Map<LottoRank, Integer> lottoRankIntegerMap;

    public LottoBundleResult(Map<LottoRank, Integer> lottoRankIntegerMap) {
        this.lottoRankIntegerMap = lottoRankIntegerMap;
    }

    public int getRankCount(LottoRank lottoRank) {
        return lottoRankIntegerMap.getOrDefault(lottoRank, 0);
    }
}
