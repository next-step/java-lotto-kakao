package domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {

    @DisplayName("총 상금을 계산한다")
    @Test
    void calculate_total_prize() {
        EnumMap<WinningStatus, Integer> counts = emptyCounts();
        counts.put(WinningStatus.FIRST, 1);
        counts.put(WinningStatus.FIFTH, 2);

        LottoResult lottoResult = new LottoResult(counts);

        assertThat(lottoResult.totalPrize()).isEqualTo(2_000_010_000L);
    }

    @DisplayName("수익률을 계산한다")
    @Test
    void calculate_total_rate() {
        EnumMap<WinningStatus, Integer> counts = emptyCounts();
        counts.put(WinningStatus.FIFTH, 1);

        LottoResult lottoResult = new LottoResult(counts);

        assertThat(lottoResult.totalRate()).isEqualTo(5.0);
    }

    private EnumMap<WinningStatus, Integer> emptyCounts() {
        EnumMap<WinningStatus, Integer> counts = new EnumMap<>(WinningStatus.class);
        for (WinningStatus status : WinningStatus.values()) {
            counts.put(status, 0);
        }
        return counts;
    }
}
