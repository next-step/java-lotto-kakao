package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StatsBoard {

    private final Map<WinLevel, Integer> matchedWinLevelCount;
    private final int ticketCount;

    public StatsBoard(GameScore gameScore, List<Ticket> tickets) {
        ticketCount = tickets.size();
        matchedWinLevelCount = new HashMap<>();

        for (WinLevel winLevel : WinLevel.getAll()) {
            matchedWinLevelCount.put(winLevel, 0);
        }

        for (Ticket ticket : tickets) {
            WinLevel level = ticket.getWinLevel(gameScore);
            matchedWinLevelCount.put(level, matchedWinLevelCount.getOrDefault(level, 0) + 1);
        }
    }

    public int getLevelCount(WinLevel winLevel) {
        return matchedWinLevelCount.get(winLevel);
    }

    public double getProfitRatio() {
        long ticketRevenue = 0L;
        long cost = 1000L * ticketCount;
        for (Entry<WinLevel, Integer> entry : matchedWinLevelCount.entrySet()) {
            ticketRevenue += entry.getKey().getPrice() * entry.getValue();
        }
        return (double) ticketRevenue / cost;
    }
}