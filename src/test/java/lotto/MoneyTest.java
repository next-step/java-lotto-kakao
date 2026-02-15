package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("1500원 단위 입력 예외처리")
    void 단위_예외_테스트(){
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(1500))
                .withMessage("천원 단위로만 입력이 가능합니다.");
    }

    @Test
    void 음수_돈_예외_테스트(){
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(-1000))
                .withMessage("양수로만 입력이 가능합니다.");
    }
}
