package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class LottoNumberTest {

    @Test
    @DisplayName("올바른 번호(1-45)일 때")
    public void validateNumber(){
        assertThatCode(() -> new LottoNumber(5)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("경계값 - 최소값 1")
    public void boundaryMin() {
        assertThatCode(() -> new LottoNumber(1)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("경계값 - 최대값 45")
    public void boundaryMax() {
        assertThatCode(() -> new LottoNumber(45)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("경계값 - 0은 유효하지 않음")
    public void boundaryBelowMin() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("경계값 - 46은 유효하지 않음")
    public void boundaryAboveMax() {
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호 일치 여부 검사 - 일치할때")
    public void isEqual(){
        LottoNumber number1 = new LottoNumber(1);
        LottoNumber number2 = new LottoNumber(1);
        assertThat(number1).isEqualTo(number2);
    }

    @Test
    @DisplayName("로또 번호 일치 여부 검사 - 일치하지 않을때")
    public void isNotEqual(){
        LottoNumber number1 = new LottoNumber(1);
        LottoNumber number2 = new LottoNumber(2);
        assertThat(number1).isNotEqualTo(number2);
    }

}
