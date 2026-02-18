package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @Test
    @DisplayName("당첨 로또는 당첨 번호와 보너스 번호를 보관한다")
    void success() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = WinningLotto.of(lotto, bonusNumber);

        assertThat(winningLotto.getLotto().getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(winningLotto.getBonusNumber()).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호가 범위를 벗어나는 경우")
    void fail_bonusNumberRange() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        int bonusNumber = 46;

        assertThatThrownBy(() -> WinningLotto.of(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoNumber.RANGE_FAIL_MSG);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되는 경우")
    void fail_bonusNumberDuplicate() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        int bonusNumber = 1;

        assertThatThrownBy(() -> WinningLotto.of(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WinningLotto.BONUS_DUPLICATE_FAIL_MSG);
    }

    @Test
    @DisplayName("countByStatus는 플레이어 로또들의 당첨 결과를 집계한다")
    void countByStatus() {
        WinningLotto winningLotto = WinningLotto.of(new Lotto(1, 2, 3, 4, 5, 6), 7);

        Lottos playerLottos = Lottos.from(List.of(
                new Lotto(1, 2, 3, 4, 5, 6), // 6개 일치
                new Lotto(1, 2, 3, 4, 5, 7), // 5개+보너스
                new Lotto(1, 2, 3, 4, 5, 8), // 5개
                new Lotto(1, 2, 3, 4, 10, 11) // 4개
        ));

        Map<LottoStatus, Integer> counts = winningLotto.countByStatus(playerLottos);

        assertThat(counts.getOrDefault(LottoStatus.SIX_CORRECT, 0)).isEqualTo(1);
        assertThat(counts.getOrDefault(LottoStatus.FIVE_CORRECT_BONUS, 0)).isEqualTo(1);
        assertThat(counts.getOrDefault(LottoStatus.FIVE_CORRECT, 0)).isEqualTo(1);
        assertThat(counts.getOrDefault(LottoStatus.FOUR_CORRECT, 0)).isEqualTo(1);
    }

}
