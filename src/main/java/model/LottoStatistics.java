package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LottoStatistics {

    private final Map<Rank, Integer> rankCountMap;
    private final int lottoCount;

    public LottoStatistics(List<Lotto> lottos, LottoResult lottoResult) {
        lottoCount = lottos.size();
        rankCountMap = new HashMap<>();

        for (Rank rank : Rank.values()) {
            rankCountMap.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank level = lotto.getRank(lottoResult);
            rankCountMap.put(level, rankCountMap.getOrDefault(level, 0) + 1);
        }
    }

    public int getLevelCount(Rank rank) {
        return rankCountMap.get(rank);
    }

    public double getProfitRates() {
        long ticketRevenue = 0L;
        long cost = 1000L * lottoCount;
        for (Entry<Rank, Integer> entry : rankCountMap.entrySet()) {
            ticketRevenue += entry.getKey().getPrice() * entry.getValue();
        }
        return (double) ticketRevenue / cost;
    }
}
