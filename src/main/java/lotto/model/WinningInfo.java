package lotto.model;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class WinningInfo {
    private final Map<WinningRank, Integer> info;

    public WinningInfo() {
        this.info = new TreeMap<>();
        Arrays.stream(WinningRank.values())
                .map(rank -> info.put(rank, 0));
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

    public int getRankCount(WinningRank rank) {
        return info.getOrDefault(rank, 0);
    }
}

