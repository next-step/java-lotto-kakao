package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoResultTest {

    LottoNumber lottoNumber1 = new LottoNumber(1);
    LottoNumber lottoNumber2 = new LottoNumber(2);
    LottoNumber lottoNumber3 = new LottoNumber(3);
    LottoNumber lottoNumber4 = new LottoNumber(4);
    LottoNumber lottoNumber5 = new LottoNumber(5);
    LottoNumber lottoNumber6 = new LottoNumber(6);

    // 메인 넘버와 보너스 넘버는 겹칠 수 없다.
    @Test
    void validateTest() {
        assertThatThrownBy(() -> new LottoResult(
                new LottoNumbers(Arrays.asList(
                        lottoNumber1, lottoNumber2, lottoNumber3, lottoNumber4, lottoNumber5, lottoNumber6)),
                new LottoNumber(6)
        )).isInstanceOf(IllegalArgumentException.class);
    }
}
