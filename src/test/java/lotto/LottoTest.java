package lotto;

import lotto.enums.LottoStatus;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    @Test
    void makeUserLotto() {
        Lotto lotto = new Lotto();

        Set<Ball> set = new HashSet<Ball>(lotto.getBalls());
        assertThat(set.size()).isEqualTo(6);
        assertThat(lotto.getStatus()).isEqualTo(LottoStatus.ZERO);
        assertThat(lotto.getBonus()).isEqualTo(new Ball(0));
    }
}
