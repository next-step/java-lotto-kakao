package lotto;

import money.Money;

import java.util.HashMap;
import java.util.Map;

public class LottoBundleResultBuilder {
    private final Map<LottoRank, Integer> counter;
    private Money totalFee;

    public LottoBundleResultBuilder() {
        this.counter = new HashMap<>();
        this.totalFee = Money.won(0L);
    }

    public void count(LottoRank lottoRank) {
        counter.put(lottoRank, counter.getOrDefault(lottoRank, 0) + 1);
    }

    public void addFee(Money fee) {
        totalFee = totalFee.plus(fee);
    }

    public LottoBundleResult build() {
        return new LottoBundleResult(Map.copyOf(counter), totalFee);
    }
}
