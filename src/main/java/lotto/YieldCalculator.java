package lotto;

public class YieldCalculator {
    private YieldCalculator() {
    }

    public static double calculate(Money totalWinningMoney, Money purchasedMoney) {
        if (purchasedMoney.money() == 0) {
            return 0;
        }
        double yield = (double) totalWinningMoney.money() / purchasedMoney.money();
        return Math.floor(yield * 100) / 100.0;
    }
}
