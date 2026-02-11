package lotto.model;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class WinningInfo {
    private final Map<WinningRank, Integer> info;

    public WinningInfo() {
        this.info = new TreeMap<>();
        Arrays.stream(WinningRank.values())
                .forEach(rank -> info.put(rank, 0));
    }

    public void addResult(WinningRank rank) {
        info.put(rank, info.getOrDefault(rank,0) + 1);
    }


    public Money getTotalPrice() {
        Money total = new Money(0);
        for (Map.Entry<WinningRank, Integer> entry : info.entrySet()) {
            WinningRank k = entry.getKey();
            Integer v = entry.getValue();
            total = total.sum(k.winningPrice.multiple(v));
        }
        return total;
    }

    public String getStatisticsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n당첨 통계\n");
        sb.append("---------\n");
        for (WinningRank rank : WinningRank.getValidRanks()) {
            appendRankResult(sb, rank);
        }
        return sb.toString().trim();
    }

    private void appendRankResult(StringBuilder sb, WinningRank rank) {
        int count = info.getOrDefault(rank, 0);
        sb.append(rank.getInfoString()).append("- ").append(count).append("개\n");
    }
}

