package lotto;

import lotto.enums.LottoStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoTest {
    @Test
    void makeUserLotto() {
        Lotto lotto = new Lotto();

        Set<Ball> set = new HashSet<Ball>(lotto.getBalls());
        assertThat(set.size()).isEqualTo(6);
        assertThat(lotto.getStatus()).isEqualTo(LottoStatus.ZERO);
        assertThat(lotto.getBonus()).isEqualTo(new Ball(0));
    }

    @Test
    void makeAnswerLotto() {
        List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
                new Ball(4), new Ball(5), new Ball(6)));
        Ball bonus = new Ball(7);
        Lotto lotto = new Lotto(answer,bonus);

        Set<Ball> set = new HashSet<Ball>(lotto.getBalls());
        assertThat(set.size()).isEqualTo(6);
        assertThat(lotto.getStatus()).isEqualTo(LottoStatus.ANSWER);
        assertThat(lotto.getBonus()).isEqualTo(bonus);
    }

    @Test
    void 당첨번호_개수가_적은경우() {
        List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(2), new Ball(3),
                new Ball(4), new Ball(5), new Ball(6)));
        Ball bonus = new Ball(1);
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(answer,bonus));
    }

    @Test
    void 당첨번호가_중복된_경우() {
        List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
                new Ball(4), new Ball(5), new Ball(5)));
        Ball bonus = new Ball(7);
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(answer,bonus));
    }

    @Test
    void 보너스와_당첨번호가_중복이되면_안된다() {
        List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
                new Ball(4), new Ball(5), new Ball(6)));
        Ball bonus = new Ball(1);
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(answer,bonus));
    }
}
