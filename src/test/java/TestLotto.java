import model.Lotto;
import model.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestLotto {

    @Test
    void 로또_번호는_6개의_수로_이루어져야_한다() {
        Lotto lotto = new Lotto(List.of(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
        ));

        assertThat(lotto.size()).isEqualTo(6);
    }

    @Test
    void 로또번호_값객체_가변인자로도_생성할_수_있다() {
        Lotto lotto = new Lotto(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
        );

        assertThat(lotto.size()).isEqualTo(6);
    }

    @Test
    void 로또_번호가_6개의_수가_아닐때_에러를_Throw() {
        assertThatThrownBy(() -> new Lotto(1, 2, 3, 4, 5))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("로또 번호 개수가 6개가 아닙니다.");
    }

    @Test
    void 로또_번호는_모두_달라야_한다() {
        assertThatThrownBy(() -> new Lotto(1, 2, 2, 3, 4, 5))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다");
    }

    @Test
    void 로또_번호는_모두_1에서_45까지() {
        assertThatThrownBy(() -> new Lotto(1, 2, 3, 0, 4, 5))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("로또 번호는 1~45까지의 숫자여야 한다.");
    }

    @Test
    void 주어진_당첨번호와의_일치_개수를_계산한다() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        List<Integer> winningNumbers = List.of(1, 2, 3, 7, 8, 9);

        assertThat(lotto.countMatches(winningNumbers)).isEqualTo(3);
    }

    @Test
    void 보너스_번호_일치_여부를_판단한다() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);

        assertThat(lotto.hasBonusNumber(6)).isEqualTo(true);
        assertThat(lotto.hasBonusNumber(7)).isEqualTo(false);
    }
}
