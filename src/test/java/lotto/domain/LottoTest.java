package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.Lotto.*;
import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    private Lotto lotto;

    @Test
    @DisplayName("성공 케이스")
    void success() {
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.getNumbers()).hasSize(6);
        assertThat(lotto.getNumbers())
                .extracting(LottoNumber::getNumber)
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("중복된 숫자를 입력하는 경우 예외처리 할 수 있다.")
    void fail_duplicateLotto() {
        assertThatThrownBy(() -> {
            lotto = new Lotto(List.of(1,2,3,4,5,5));
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(DUPLICATE_NUMBER_EXCEPTION);
    }

    @Test
    @DisplayName("숫자가 6개가 아닌 경우 예외처리 할 수 있다.")
    void fail_lottoNumberCountIsNotSix() {
        assertThatThrownBy(() -> {
            lotto = new Lotto(List.of(1,2,3,4,5,6,7));
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(NOT_SIX_NUMBERS_EXCEPTION);
    }

    @Test
    @DisplayName("당첨 로또와 사용자 로또의 겹치는 숫자의 개수를 구할 수 있다.")
    void matchTest() {
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto userLotto = new Lotto(List.of(1,2,3,7,8,9));

        int count = lotto.matchCount(userLotto);

        assertThat(count).isEqualTo(3);
    }
}
