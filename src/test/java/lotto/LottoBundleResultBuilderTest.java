package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoBundleResultBuilderTest {
    @Test
    void createSuccess() {
        LottoBundleResultBuilder lottoBundleResultBuilder = new LottoBundleResultBuilder();
        lottoBundleResultBuilder.count(LottoRank.FIRST);
        LottoBundleResult lottoBundleResult = lottoBundleResultBuilder.build();
        assertThat(1).isEqualTo(lottoBundleResult.getRankCount(LottoRank.FIRST));
        assertThat(0).isEqualTo(lottoBundleResult.getRankCount(LottoRank.SECOND));
    }
}
