package lotto;

public class YieldCalculator {
    private YieldCalculator() {
    }

    public static double calculate(long totalWinningMoney, long purchasedMoney) {
        if (purchasedMoney == 0) {
            return 0;
        }
        double yield = (double) totalWinningMoney / purchasedMoney;
        return Math.floor(yield * 100) / 100.0;
    }
}
