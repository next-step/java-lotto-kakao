package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class LottoNumberTest {

    @Test
    @DisplayName("올바른 번호(1-45)일 때")
    public void validateNumber(){
        assertThatCode(() -> new LottoNumber(5));
    }

    @Test
    @DisplayName("올바르지 않은 번호일 때")
    public void invalidateNumber(){
        assertThatThrownBy(() -> new LottoNumber(64))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("로또 번호는 1-45 사이 값이어야 합니다");
    }

    @Test
    @DisplayName("로또 번호 일치 여부 검사 - 일치할때")
    public void isEqual(){
        LottoNumber number1 = new LottoNumber(1);
        LottoNumber number2 = new LottoNumber(1);
        assertThat(number1).isEqualTo(number2);
    }

}