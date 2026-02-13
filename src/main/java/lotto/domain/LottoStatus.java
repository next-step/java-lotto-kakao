package lotto.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiPredicate;

public enum LottoStatus {

    SIX_CORRECT(2_000_000_000L, (match, bonus) -> match == 6),
    FIVE_CORRECT_BONUS(30_000_000L, (match, bonus) -> match == 5 && bonus),
    FIVE_CORRECT(1_500_000L, (match, bonus) -> match == 5 && !bonus),
    FOUR_CORRECT(50_000L, (match, bonus) -> match == 4),
    THREE_CORRECT(5_000L, (match, bonus) -> match == 3),
    FAIL(0L, (match, bonus) -> true)
    ;

    private final long price;
    private final BiPredicate<Integer, Boolean> matchPredicate;

    LottoStatus(long price, BiPredicate<Integer, Boolean> matchPredicate) {
        this.price = price;
        this.matchPredicate = matchPredicate;
    }

    public long getPrice() {
        return price;
    }

    public static LottoStatus judgeGameStatus(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                    .filter(status -> status.matchPredicate.test(matchCount, hasBonus))
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

}
