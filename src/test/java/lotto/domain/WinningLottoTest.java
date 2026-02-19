package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.WinningLotto.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class WinningLottoTest {

    private User user;

    @BeforeEach
    void beforeEach() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        Lotto lotto3 = new Lotto(List.of(13, 14, 15, 16, 17, 18));
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3);

        this.user = new User(new Price(3000), lottos, 3);
    }

    @Test
    @DisplayName("성공 케이스")
    void success() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.of(7);
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        assertThat(winningLotto.getLotto().getNumbers())
                .extracting(LottoNumber::getNumber)
                .containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(winningLotto.getBonusNumber().getNumber()).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되는 경우 예외처리 할 수 있다.")
    void fail_bonusNumberDuplicate() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            LottoNumber bonusNumber = LottoNumber.of(1);
            WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_DUPLICATE_EXCEPTION);
    }

    @Test
    @DisplayName("사용자의 로또 번호를 기반으로 수익과 수익률을 계산할 수 있다.")
    void calculate() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), LottoNumber.of(7));

        LottoResult result = winningLotto.calculate(this.user.getLottos(), this.user.getPrice());
        long profit = result.getProfit();
        double profitRate = result.getProfitRate();

        assertThat(result.getStatuses().get(LottoStatus.SIX_CORRECT)).isEqualTo(1);
        assertThat(profit).isEqualTo(2000000000);
        assertThat(profitRate).isEqualTo(666666.6666666666);
    }

}
