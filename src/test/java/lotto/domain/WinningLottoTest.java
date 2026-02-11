package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class WinningLottoTest {

    @Test
    @DisplayName("성공케이스")
    void success() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        assertThat(winningLotto.getLotto().getNumbers()).containsExactly(1,2,3,4,5,6);
        assertThat(winningLotto.getBonusNumber()).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호가 범위를 벗어나는 경우")
    void fail_bonusNumberRange() {

        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            int bonusNumber = 46;
            WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoNumber.RANGE_FAIL_MSG);

    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되는 경우")
    void fail_bonusNumberDuplicate() {

        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            int bonusNumber = 1;
            WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨번호와 중복된 숫자를 보너스 번호로 등록할 수 없습니다.");

    }

}
