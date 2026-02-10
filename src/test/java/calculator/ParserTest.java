package calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    @DisplayName("커스텀 구분자가 없는 정상 입력인 경우")
    void inputTest1() {
        String successInput = "1,2,3";
        Parser parser = new Parser(successInput);

        Assertions.assertThat(parser.getNumber().getNumbers()).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("커스텀 구분자가 포함된 정상 입력인 경우")
    void inputTest2() {
        Separator input = new Separator("//;\n,1,2,3");

    }

    @Test
    @DisplayName("등록한 구분자 이외의 구분자를 사용한 경우")
    void inputExceptionTest3() {
        Assertions.assertThatThrownBy(() -> {
            Parser parser = new Parser("1^2,3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("등록되지 않은 커스텀 구분자가 입력되었습니다.");
    }

    @Test
    @DisplayName("커스텀 구분자가 숫자인 경우")
    void inputExceptionTest4() {
        Assertions.assertThatThrownBy(() -> {
            Separator input = new Separator("//6\n1,2,3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("커스텀 구분자는 숫자가 될 수 없습니다.");
    }

    @Test
    @DisplayName("커스텀 구분자가 숫자인 경우")
    void inputExceptionTest5() {
        Assertions.assertThatThrownBy(() -> {
            Separator input = new Separator("///;\n1;2;3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구분자를 연속적으로 사용한 경우")
    void inputExceptionTest6() {
        Assertions.assertThatThrownBy(() -> {
            Parser parser = new Parser("1,2,,3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}