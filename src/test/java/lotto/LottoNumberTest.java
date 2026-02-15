package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("1부터 45사이의 숫자 확인")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void 숫자_범위_테스트(int number){
        LottoNumber lottoNumber = new LottoNumber(number);
        Assertions.assertThat(lottoNumber.toNumber()).isBetween(1, 45);
    }
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("정상 범위 바깥 예외처리 확인")
    void 범위_예외_테스트(int number){
        Assertions.assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자 범위를 벗어났습니다.");
    }
}
