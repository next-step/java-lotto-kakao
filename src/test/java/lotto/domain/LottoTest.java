package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("정상적으로 로또 번호를 입력한 경우")
    void lottoSuccessTest() {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);

        Assertions.assertThat(lotto.getNumbers()).hasSize(6);
        Assertions.assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("중복된 숫자를 입력하는 경우")
    void lottoFailTest2() {
        Assertions.assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(1, 2, 3, 4, 5, 5);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(Lotto.DUPLICATE_FAIL_MSG);
    }

    @Test
    @DisplayName("숫자가 6개가 아닌 경우")
    void lottoFailTest3() {
        Assertions.assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6, 7);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(Lotto.COUNT_FAIL_MSG);
    }
}
