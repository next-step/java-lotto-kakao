package calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    @DisplayName("커스텀 구분자가 없는 정상 입력인 경우")
    void success() {
        String successInput = "1,2,3";

        Assertions.assertThat(Parser.parse(successInput).getNumbers()).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("커스텀 구분자가 있는 정상 입력인 경우")
    void success_customSeparator() {
        String successInput = "//;\n1,2,3";

        Assertions.assertThat(Parser.parse(successInput).getNumbers()).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("등록한 구분자 이외의 구분자를 사용한 경우")
    void fail_unknownSeparator() {
        Assertions.assertThatThrownBy(() -> Parser.parse("1^2,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("등록되지 않은 커스텀 구분자가 입력되었습니다.");
    }

    @Test
    @DisplayName("구분자를 연속적으로 사용한 경우")
    void fail_consecutiveSeparator() {
        Assertions.assertThatThrownBy(() -> Parser.parse("1,2,,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구분자는 연속적으로 사용할 수 없습니다.");
    }

}