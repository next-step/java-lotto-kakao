package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StatsBoard {

    private final Map<Rank, Integer> matchedWinLevelCount;
    private final int ticketCount;

    public StatsBoard(GameScore gameScore, List<Lotto> lottos) {
        ticketCount = lottos.size();
        matchedWinLevelCount = new HashMap<>();

        for (Rank rank : Rank.values()) {
            matchedWinLevelCount.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank level = lotto.getRank(gameScore);
            matchedWinLevelCount.put(level, matchedWinLevelCount.getOrDefault(level, 0) + 1);
        }
    }

    public int getLevelCount(Rank rank) {
        return matchedWinLevelCount.get(rank);
    }

    public double getProfitRatio() {
        long ticketRevenue = 0L;
        long cost = 1000L * ticketCount;
        for (Entry<Rank, Integer> entry : matchedWinLevelCount.entrySet()) {
            ticketRevenue += entry.getKey().getPrice() * entry.getValue();
        }
        return (double) ticketRevenue / cost;
    }
}
