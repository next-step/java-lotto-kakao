package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoInputValidatorTest {

    @Test
    @DisplayName("입력값이 null인 경우")
    void null_input_test() {
        assertThatThrownBy(() -> LottoInputValidator.parseInt(null))
                .isInstanceOf(NumberFormatException.class);
    }

    @Test
    @DisplayName("입력값에 문자가 포함된 경우")
    void invalid_char_input_test() {
        assertThatThrownBy(() -> LottoInputValidator.parseInt("test"))
                .isInstanceOf(NumberFormatException.class);
    }

    @Test
    @DisplayName("정상적인 입력")
    void correct_input_test() {
        String userInput = "15";
        int convertValue = LottoInputValidator.parseInt(userInput);

        assertThat(Integer.parseInt(userInput)).isEqualTo(convertValue);
    }
}
