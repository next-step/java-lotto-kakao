package domain.lotto;

import domain.winning.WinningLotto;
import domain.winning.WinningStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class WinningLottoTest {
    @Test
    @DisplayName("당첨 번호를 생성한다.")
    void winning() {
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        WinningLotto winningLotto = new WinningLotto(input, bonusNumber);
        Assertions.assertNotNull(winningLotto);
    }

    @Test
    @DisplayName("당첨 여부를 확인한다. (4개 일치)")
    void check_winning() {
        Lotto lotto = new Lotto(List.of(1,2,3,4,8,9));
        WinningLotto winningLotto = new WinningLotto(List.of(1,2,3,4,5,6), 7);
        WinningStatus winningStatus = winningLotto.compare(lotto);
        Assertions.assertEquals(WinningStatus.FORTH, winningStatus);
    }

    @Test
    @DisplayName("당첨 여부를 확인한다. (5개 일치, 보너스 볼 일치)")
    void check_winning_bonus() {
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,7));
        WinningLotto winningLotto = new WinningLotto(List.of(1,2,3,4,5,6), 7);
        WinningStatus winningStatus = winningLotto.compare(lotto);
        Assertions.assertEquals(WinningStatus.SECOND, winningStatus);
    }
}
