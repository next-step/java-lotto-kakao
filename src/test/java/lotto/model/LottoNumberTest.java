package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class LottoNumberTest {

    @Test
    @DisplayName("올바른 번호(1-45)일 때")
    public void validateNumber() {
        assertThatCode(() -> LottoNumber.of(5)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("경계값 - 최소값 1")
    public void boundaryMin() {
        assertThatCode(() -> LottoNumber.of(1)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("경계값 - 최대값 45")
    public void boundaryMax() {
        assertThatCode(() -> LottoNumber.of(45)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("경계값 - 0은 유효하지 않음")
    public void boundaryBelowMin() {
        assertThatThrownBy(() -> LottoNumber.of(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("경계값 - 46은 유효하지 않음")
    public void boundaryAboveMax() {
        assertThatThrownBy(() -> LottoNumber.of(46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호 일치 여부 검사 - 일치할때")
    public void isEqual() {
        LottoNumber number1 = LottoNumber.of(1);
        LottoNumber number2 = LottoNumber.of(1);
        assertThat(number1).isEqualTo(number2);
    }

    @Test
    @DisplayName("로또 번호 일치 여부 검사 - 일치하지 않을때")
    public void isNotEqual() {
        LottoNumber number1 = LottoNumber.of(1);
        LottoNumber number2 = LottoNumber.of(2);
        assertThat(number1).isNotEqualTo(number2);
    }

    @Test
    @DisplayName("번호 비교")
    public void compareToNumber() {
        LottoNumber number1 = LottoNumber.of(5);
        LottoNumber number2 = LottoNumber.of(7);

        assertThat(number1.compareTo(number2)).isLessThan(0);
        assertThat(number2.compareTo(number1)).isGreaterThan(0);
        assertThat(number1.compareTo(LottoNumber.of(5))).isEqualTo(0);
    }

}
