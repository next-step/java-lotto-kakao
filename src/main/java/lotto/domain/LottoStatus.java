package lotto.domain;

import java.util.Arrays;
import java.util.Map;

public enum LottoStatus {

    SIX_CORRECT(2000000000L, 6, false),
    FIVE_CORRECT_BONUS(30000000L, 5, true),
    FIVE_CORRECT(1500000L, 5, false),
    FOUR_CORRECT(50000L, 4, false),
    THREE_CORRECT(5000L, 3, false),
    FAIL(0L, -1, false)
    ;

    private final long price;
    private final int count;
    private final boolean hasBonus;

    LottoStatus(long price, int count, boolean hasBonus) {
        this.price = price;
        this.count = count;
        this.hasBonus = hasBonus;
    }

    public long getPrice() {
        return price;
    }

    public static LottoStatus judgeGameStatus(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(status -> status.match(matchCount, hasBonus))
                .findFirst()
                .orElse(FAIL);
    }

    public static long totalPrize(Map<LottoStatus, Integer> counts) {
        long sum = 0;
        for (var e: counts.entrySet()) {
            sum += (long) e.getKey().getPrice() * e.getValue();
        }
        return sum;
    }

    public boolean match(int matchCount, boolean hasBonus) {
        if (this.count != matchCount) return false;
        if (this.count == 5) return this.hasBonus == hasBonus;
        return true;
    }



}
