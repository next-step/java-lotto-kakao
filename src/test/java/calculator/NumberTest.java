package calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    @DisplayName("입력에 문자열이 포함된 예외 경우")
    void inputExceptionTest1() {
        Assertions.assertThatThrownBy(() -> {
            calculator.Number number = new calculator.Number("1 2 a");
        }).isInstanceOf(RuntimeException.class).hasMessage("숫자 형식이 아닙니다.");
    }

    @Test
    @DisplayName("음수를 입력받은 경우")
    void inputExceptionTest2() {
        Assertions.assertThatThrownBy(() -> {
                    calculator.Number number = new calculator.Number("-1 2 3");
                })
                .isInstanceOf(RuntimeException.class)
                .hasMessage("음수가 입력되었습니다.");
    }

    @Test
    @DisplayName("입력 받은 값을 계산 할 수 있다.")
    void calculationTest() {
        calculator.Number number = new Number("1 2 3");

        Assertions.assertThat(number.calculate()).isEqualTo(6);
    }

}