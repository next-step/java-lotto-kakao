package calcucator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberTest {
    @Test
    void emptyReturnZero() {
        String text = "";
        calculator.Number number = new calculator.Number(text);
        assertThat(number.getValue()).isEqualTo(0);
    }

    @Test
    void nullReturnZero() {
        String text = null;
        calculator.Number number = new calculator.Number(text);
        assertThat(number.getValue()).isEqualTo(0);
    }

    @Test
    void StringReturnNumber() {
        String text = "123";
        calculator.Number number = new calculator.Number(text);
    }
}
