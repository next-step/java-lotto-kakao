package lotto;

import money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoBundleResultBuilderTest {
    @Test
    void createSuccess() {
        LottoBundleResultBuilder lottoBundleResultBuilder = new LottoBundleResultBuilder(Money.won(Lotto.PRICE));
        lottoBundleResultBuilder.count(LottoRank.FIRST);
        LottoBundleResult lottoBundleResult = lottoBundleResultBuilder.build();
        assertThat(1).isEqualTo(lottoBundleResult.getRankCount(LottoRank.FIRST));
        assertThat(0).isEqualTo(lottoBundleResult.getRankCount(LottoRank.SECOND));
        assertThat(2000000.0).isEqualTo(lottoBundleResult.calculateProfitRate());
    }
}
