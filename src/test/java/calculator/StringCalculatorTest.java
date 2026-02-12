package calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    void blank_count_zero() {
        assertThat(StringCalculator.add(null)).isEqualTo(0);
        assertThat(StringCalculator.add("")).isEqualTo(0);
    }

    @Test
    void default_delimiter() {
        assertThat(StringCalculator.add("1,2,3")).isEqualTo(6);
        assertThat(StringCalculator.add("1,2:3")).isEqualTo(6);
        assertThat(StringCalculator.add("1,2")).isEqualTo(3);
    }

    @Test
    void custom_delimiter() {
        int result = StringCalculator.add("//;\n1;2;3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    void throws_when_negative() {
        assertThatThrownBy(() -> StringCalculator.add("-1,2,3"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("음수");
    }

    @Test
    void throws_when_not_number() {
        assertThatThrownBy(() -> StringCalculator.add("a,2,3"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("숫자 이외");
    }
}
