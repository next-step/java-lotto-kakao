package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static model.Constants.COST;

public class LottoStatistics {

    private final int lottoCount;
    private final Map<Rank, Integer> rankCountMap;

    public LottoStatistics(List<Lotto> lottos, LottoResult lottoResult) {
        lottoCount = lottos.size();
        rankCountMap = new HashMap<>();

        for (Rank rank : Rank.values()) {
            rankCountMap.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank rank = lotto.getRank(lottoResult);
            rankCountMap.put(rank, rankCountMap.getOrDefault(rank, 0) + 1);
        }
    }

    public int getRankCount(Rank rank) {
        return rankCountMap.get(rank);
    }

    public double getProfitRates() {
        long profit = 0L;
        long cost = (long) COST * lottoCount;
        for (Entry<Rank, Integer> entry : rankCountMap.entrySet()) {
            profit += entry.getKey().getPrice() * entry.getValue();
        }
        return (double) profit / cost;
    }
}
