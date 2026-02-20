import domains.Lotto;
import domains.LottoNumber;
import domains.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {
    @Test
    public void 로또_번호의_개수가_6개가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(1, 2, 3, 4, 5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(1, 2, 2, 4, 5, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 특정_번호가_로또에_포함되어_있는지_확인한다() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        LottoNumber number = new LottoNumber(2);

        assertTrue(lotto.contains(number));
    }

    @Test
    public void 로또를_생성하면_번호가_오름차순으로_정렬된다() {
        Lotto lotto1 = new Lotto(6, 5, 4, 3, 2, 1);
        Lotto lotto2 = new Lotto(1, 2, 3, 4, 5, 6);

        assertEquals(lotto1, lotto2);
    }

    @Test
    public void 당첨_번호와_3개가_일치하면_5등으로_판단한다() {
        Lotto winningLotto = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(1, 2, 3, 9, 10, 11);

        assertEquals(Rank.FIFTH, lotto.match(winningLotto, new LottoNumber(44)));
    }
}
