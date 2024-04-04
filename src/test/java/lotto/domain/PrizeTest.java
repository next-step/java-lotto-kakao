package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PrizeTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void 번호가_2개이하로_일치하면_NOTHING(int matchedCount) {
        Prize prize = Prize.evaluate(matchedCount, false);
        Assertions.assertThat(prize).isEqualTo(Prize.NOTHING);
    }

    @Test
    void 번호3개가_일치하면_5등() {
        Prize prize = Prize.evaluate(3, false);
        Assertions.assertThat(prize).isEqualTo(Prize.FIFTH);
    }

    @Test
    void 번호4개가_일치하면_4등() {
        Prize prize = Prize.evaluate(4, false);
        Assertions.assertThat(prize).isEqualTo(Prize.FOURTH);
    }

    @Test
    void 번호5개가_일치하고_보너스번호가_일치하지_않으면_3등() {
        Prize prize = Prize.evaluate(5, false);
        Assertions.assertThat(prize).isEqualTo(Prize.THIRD);
    }

    @Test
    void 번호5개가_일치하고_보너스번호가_일치하면_2등() {
        Prize prize = Prize.evaluate(5, true);
        Assertions.assertThat(prize).isEqualTo(Prize.SECOND);
    }

    @Test
    void 번호6개가_일치하면_1등() {
        Prize prize = Prize.evaluate(6, false);
        Assertions.assertThat(prize).isEqualTo(Prize.FIRST);
    }

    @Test
    void 번호3개가_일치하면_1등() {
        Prize prize = Prize.evaluate(3, false);
        Assertions.assertThat(prize).isEqualTo(Prize.FIFTH);
    }
}
