package lotto.domain;

import java.util.Arrays;
import java.util.Map;

public enum LottoStatus {

    SIX_CORRECT(2_000_000_000L, 6, false, false),
    FIVE_CORRECT_BONUS(30_000_000L, 5, true, true),
    FIVE_CORRECT(1_500_000L, 5, false, true),
    FOUR_CORRECT(50_000L, 4, false, false),
    THREE_CORRECT(5_000L, 3, false, false),
    FAIL(0L, -1, false, false)
    ;

    private final long price;
    private final int count;
    private final boolean expectedBonus;
    private final boolean bonusRelevant;

    LottoStatus(long price, int count, boolean expectedBonus, boolean bonusRelevant) {
        this.price = price;
        this.count = count;
        this.expectedBonus = expectedBonus;
        this.bonusRelevant = bonusRelevant;
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
            sum += e.getKey().getPrice() * (long) e.getValue();
        }
        return sum;
    }

    private boolean match(int matchCount, boolean hasBonus) {
        if (this.count != matchCount) {
            return false;
        }
        if (!bonusRelevant) {
            return true;
        }
        return this.expectedBonus == hasBonus;
    }
}
