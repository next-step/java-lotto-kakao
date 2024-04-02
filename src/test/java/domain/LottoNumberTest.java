package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("LottoNumber 관련 테스트")
class LottoNumberTest {
    @Test
    void int값을_이용하여_생성() {
        LottoNumber number = LottoNumber.of(1);
        assertThat(number).isNotNull();
        assertThat(number.value()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"1", "2", "3", "4", "5"})
    void isMatched는_입력_값과_일치_여부를_확인(int value) {
        LottoNumber number = LottoNumber.of(value);
        assertThat(number.isMatched(value)).isTrue();
    }

    @Test
    void LottoNumber는_대소비교가_가능() {
        LottoNumber n1 = LottoNumber.of(1);
        LottoNumber n2 = LottoNumber.of(2);
        assertThat(n1).isLessThan(n2);
    }

    @Test
    void LottoNumber는_동치를_파악_가능() {
        LottoNumber n1 = LottoNumber.of(1);
        LottoNumber n2 = LottoNumber.of(1);
        assertThat(n1).isEqualTo(n2);
    }

    @Test
    void 범위를_벗어난_값에_대해_RuntimeException을_발생() {
        assertThatThrownBy(() -> LottoNumber.of(100)).isInstanceOf(IllegalArgumentException.class);
    }
}