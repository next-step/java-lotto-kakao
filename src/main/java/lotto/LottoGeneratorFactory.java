package lotto;

public class LottoGeneratorFactory {
    public static LottosGenerator create(long totalCount, LottoForm lottoForm) {
        int manualCount = lottoForm.size();
        long autoCount = totalCount - manualCount;

        return new CompositeLottosGenerator(
                new ManualLottosGenerator(lottoForm),
                new AutoLottosGenerator(autoCount)
        );
    }
}
