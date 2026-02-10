package lotto;

import java.util.HashMap;
import java.util.Map;

public class LottoBundleResultBuilder {
    private final Map<LottoRank, Integer> counter;

    public LottoBundleResultBuilder() {
        this.counter = new HashMap<>();
    }

    public void count(LottoRank lottoRank) {
        counter.put(lottoRank, counter.getOrDefault(lottoRank, 0) + 1);
    }

    public LottoBundleResult build() {
        return new LottoBundleResult(Map.copyOf(counter));
    }
}
