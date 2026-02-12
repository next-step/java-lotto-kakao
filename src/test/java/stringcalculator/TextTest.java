package stringcalculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TextTest {

    @Test
    @DisplayName("빈 문자열 -> 0 반환")
    void emptyCharTest() {
        Text text = new Text("");

        Assertions.assertThat(text.getValue()).isZero();
    }

    @Test
    @DisplayName("null -> 0 반환")
    void emptyNullTest() {
        Text text = new Text(null);

        Assertions.assertThat(text.getValue()).isZero();
    }

    @Test
    @DisplayName("문자열(숫자 하나)을 int type으로 변환")
    void parseStringToIntTest() {
        Text text = new Text("1");

        Assertions.assertThat(text.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("컴마로 구분된 두 숫자 입력")
    void parseNumber2WithSplitter() {
        Text text = new Text("1,2");

        int[] textArr = text.getIntArr();

        assertThat(textArr).hasSize(2);

        assertThat(textArr[0]).isEqualTo(1);
        assertThat(textArr[1]).isEqualTo(2);
    }

    @Test
    @DisplayName("컴마로 구분된 두 숫자 합")
    void addNumber2WithSplitter() {
        Text text = new Text("1,2");

        Assertions.assertThat(text.getValue()).isEqualTo(3);
    }

    @Test
    @DisplayName("구분자로 세미콜론 사용 가능")
    void useSemicolonSplitter() {
        Text text = new Text("1,:2");

        Assertions.assertThat(text.getValue()).isEqualTo(3);
    }

    @Test
    @DisplayName("구분자로 커스텀 구분자 사용 가능")
    void useCustomSplitter() {
        Text text = new Text("//;\n1;2;3");

        Assertions.assertThat(text.getValue()).isEqualTo(6);
    }

    @Test
    @DisplayName("음수일 경우 예외 발생")
    void exceptionForNegativeNumber() {

        assertThatRuntimeException()
                .isThrownBy(() -> new Text("-1, 2, 3"));
    }
}
