package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.LottoNumber.*;

public class LottoNumberTest {

    private LottoNumber lottoNumber;

    @Test
    @DisplayName("성공 케이스")
    void success() {
        lottoNumber = LottoNumber.of(6);

        Assertions.assertThat(lottoNumber.getNumber()).isEqualTo(6);
    }

    @Test
    @DisplayName("1 ~ 45 범위를 넘어가는 숫자의 로또 번호가 입력된 경우 예외처리 할 수 있다.")
    void fail_rangeError() {
        Assertions.assertThatThrownBy(() -> {
            lottoNumber = LottoNumber.of(46);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(NUMBER_OUT_OF_RANGE_EXCEPTION);
    }

}
