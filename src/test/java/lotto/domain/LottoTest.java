package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @Test
    @DisplayName("정상적으로 로또 번호 6개를 입력한 경우")
    void lottoSuccessTest() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);

        assertThat(lotto.getNumbers()).hasSize(6);
        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("중복된 숫자를 입력하는 경우")
    void lottoFailTest2() {
        assertThatThrownBy(() -> new Lotto(1, 2, 3, 4, 5, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Lotto.DUPLICATE_FAIL_MSG);
    }

    @Test
    @DisplayName("숫자가 6개가 아닌 경우")
    void lottoFailTest3() {
        assertThatThrownBy(() -> new Lotto(1, 2, 3, 4, 5, 6, 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Lotto.COUNT_FAIL_MSG);
    }

    @Test
    @DisplayName("matchCount는 두 로또간 일치하는 번호의 갯수를 반환한다")
    void matchCount() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto other = new Lotto(1, 2, 3, 10, 11, 12);

        assertThat(lotto.matchCount(other)).isEqualTo(3);
    }
}
