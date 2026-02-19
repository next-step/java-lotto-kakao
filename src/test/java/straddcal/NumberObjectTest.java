package straddcal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NumberObjectTest {

    @Test
    @DisplayName("올바른 숫자일 때")
    public void validateNumber(){
        assertThatCode(() -> new NumberObject("123"));
    }


    @Test
    @DisplayName("음수가 들어갔을 때")
    public void negativeNumber(){
        assertThatThrownBy(() -> new NumberObject("-123"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수 에러 발생!");
    }

    @Test
    @DisplayName("숫자가 아닐 때")
    public void notNumber() {
        assertThatThrownBy(() -> new NumberObject("[]="))
                .isInstanceOf(NumberFormatException.class);
    }

    @Test
    @DisplayName("수 합산하기")
    public void sumNumber(){
        NumberObject number = new NumberObject(123);
        NumberObject number2 = new NumberObject(100);
        number.sum(number2);
        assertThat(number.toString()).isEqualTo("223");
    }
}
