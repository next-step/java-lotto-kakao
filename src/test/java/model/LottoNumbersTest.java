package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoNumbersTest {

    LottoNumber lottoNumber1 = LottoNumber.of(1);
    LottoNumber lottoNumber2 = LottoNumber.of(2);
    LottoNumber lottoNumber3 = LottoNumber.of(3);
    LottoNumber lottoNumber4 = LottoNumber.of(4);
    LottoNumber lottoNumber5 = LottoNumber.of(5);
    LottoNumber lottoNumber6 = LottoNumber.of(6);
    LottoNumber lottoNumber7 = LottoNumber.of(7);

    // 로또 번호는 6개여야 한다.
    @Test
    void validateTest1() {
        assertThatThrownBy(() -> new LottoNumbers(Arrays.asList(
                lottoNumber1, lottoNumber2, lottoNumber3, lottoNumber4, lottoNumber5
        ))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new LottoNumbers(Arrays.asList(
                lottoNumber1, lottoNumber2, lottoNumber3, lottoNumber4, lottoNumber5, lottoNumber6, lottoNumber7
        ))).isInstanceOf(IllegalArgumentException.class);
    }

    // 로또 번호에 중복된 숫자가 있으면 안된다.
    @Test
    void validateTest2() {
        assertThatThrownBy(() -> new LottoNumbers(Arrays.asList(
                lottoNumber1, lottoNumber2, lottoNumber3, lottoNumber4, lottoNumber5, lottoNumber5
        ))).isInstanceOf(IllegalArgumentException.class);
    }
}
