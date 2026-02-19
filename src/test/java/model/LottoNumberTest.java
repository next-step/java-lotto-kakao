package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoNumberTest {

    // 로또 번호는 1 ~ 45 사이에서만 생성되어야 한다.
    @Test
    void validateTest() {
        assertThatThrownBy(() -> LottoNumber.of(0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LottoNumber.of(46)).isInstanceOf(IllegalArgumentException.class);
    }

    // 로또 번호가 같은 객체는 동일한 객체다.
    @Test
    void equalsTest() {
        LottoNumber lottoNumber1 = LottoNumber.of(1);
        LottoNumber lottoNumber2 = LottoNumber.of(1);
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }
}
