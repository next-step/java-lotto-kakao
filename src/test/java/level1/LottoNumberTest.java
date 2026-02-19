package level1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 25, 45})
    @DisplayName("1부터 45 사이의 숫자로 캐싱된 로또 번호를 가져올 수 있다.")
    void valueOfSuccess(int value) {
        LottoNumber lottoNumber = LottoNumber.valueOf(value);
        assertThat(lottoNumber.getValue()).isEqualTo(value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "25", "45"})
    @DisplayName("문자열 숫자로도 캐싱된 로또 번호를 가져올 수 있다.")
    void valueOfByStringSuccess(String value) {
        LottoNumber lottoNumber = LottoNumber.valueOf(value);
        assertThat(lottoNumber.getValue()).isEqualTo(Integer.parseInt(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    @DisplayName("1~45 범위를 벗어나는 숫자는 예외가 발생한다.")
    void valueOfRangeFail(int value) {
        assertThatThrownBy(() -> LottoNumber.valueOf(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1 - 45 범위 숫자만 가능합니다.");
    }

    @Test
    @DisplayName("같은 숫자에 대해 동일한 인스턴스를 반환하는지 확인한다. (캐싱 검증)")
    void sameInstanceCachingTest() {
        LottoNumber number1 = LottoNumber.valueOf(10);
        LottoNumber number2 = LottoNumber.valueOf(10);
        LottoNumber number3 = LottoNumber.valueOf("10");

        assertThat(number1).isEqualTo(number2);

        assertThat(number1).isSameAs(number2);
        assertThat(number2).isSameAs(number3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", " ", "", "!!"})
    @DisplayName("숫자가 아닌 문자열을 입력하면 예외가 발생한다.")
    void valueOfFormatFail(String value) {
        assertThatThrownBy(() -> LottoNumber.valueOf(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자가 아닌 값은 허용되지 않습니다.");
    }
}