package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStatusTest {

    @Test
    @DisplayName("judgeGameStatus는 일치 개수와 보너스 여부로 상태를 판정한다")
    void judgeGameStatus() {
        assertThat(LottoStatus.judgeGameStatus(6, false)).isEqualTo(LottoStatus.SIX_CORRECT);
        assertThat(LottoStatus.judgeGameStatus(5, true)).isEqualTo(LottoStatus.FIVE_CORRECT_BONUS);
        assertThat(LottoStatus.judgeGameStatus(5, false)).isEqualTo(LottoStatus.FIVE_CORRECT);
        assertThat(LottoStatus.judgeGameStatus(4, false)).isEqualTo(LottoStatus.FOUR_CORRECT);
        assertThat(LottoStatus.judgeGameStatus(3, false)).isEqualTo(LottoStatus.THREE_CORRECT);
        assertThat(LottoStatus.judgeGameStatus(2, false)).isEqualTo(LottoStatus.FAIL);
    }

    @Test
    @DisplayName("totalPrize는 상태별 개수에 따라 총 상금을 합산한다")
    void totalPrize() {
        Map<LottoStatus, Integer> counts = new EnumMap<>(LottoStatus.class);
        counts.put(LottoStatus.SIX_CORRECT, 2);
        counts.put(LottoStatus.THREE_CORRECT, 1);

        long prize = LottoStatus.totalPrize(counts);
        assertThat(prize).isEqualTo(LottoStatus.SIX_CORRECT.getPrice() * 2 + LottoStatus.THREE_CORRECT.getPrice());
    }

}