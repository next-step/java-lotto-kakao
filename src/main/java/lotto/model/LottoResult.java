package lotto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private final List<LottoRank> lottoRanks;

    public LottoResult(List<LottoRank> lottoRanks) {
        this.lottoRanks = lottoRanks;
    }

    private long calculateTotalPrize() {
        return lottoRanks.stream()
                .mapToLong(LottoRank::getPrize)
                .sum();
    }

    public float calculateReturnRate(PurchaseAmount totalPurchaseAmount) {
        return (float) calculateTotalPrize() / totalPurchaseAmount.getPurchaseAmount();
    }

    public Map<LottoRank, Integer> makeLottoResultMap() {
        Map<LottoRank, Integer> lottoResultMap = new HashMap<>();
        for (LottoRank lottoRank : LottoRank.values()) {
            lottoResultMap.put(lottoRank, 0);
        }
        for (LottoRank lottoRank : lottoRanks) {
            lottoResultMap.put(lottoRank, lottoResultMap.get(lottoRank) + 1);
        }
        lottoResultMap.remove(LottoRank.NONE);
        return lottoResultMap;
    }

    public List<LottoRank> getLottoRanks() {
        return lottoRanks;
    }
}
