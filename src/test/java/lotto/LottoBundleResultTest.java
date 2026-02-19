package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoBundleResultTest {
    @Test
    @DisplayName("존재하지 않는 등수 조회 시 0을 반환한다.")
    void returnsZeroWhenRankAbsent() {
        LottoBundleResult result = new LottoBundleResult(Map.of());
        assertThat(result.getRankCount(LottoRank.FIFTH)).isZero();
    }

    @Test
    @DisplayName("등수별 카운트를 조회한다.")
    void returnsCountWhenRankPresent() {
        LottoBundleResult result = new LottoBundleResult(Map.of(
                LottoRank.FIFTH, 1,
                LottoRank.FOURTH, 2
        ));

        assertThat(result.getRankCount(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(result.getRankCount(LottoRank.FOURTH)).isEqualTo(2);
        assertThat(result.getRankCount(LottoRank.THIRD)).isZero();
    }

    @Test
    void calculateProfitRate() {
        LottoBundleResult result = new LottoBundleResult(Map.of(
                LottoRank.FIFTH, 1,
                LottoRank.FOURTH, 2,
                LottoRank.LOSE, 5
        ));

        double rate = result.calculateProfitRate(LottoShop.PRICE.times(8));
        assertThat(13.125).isEqualTo(rate);
    }
}
