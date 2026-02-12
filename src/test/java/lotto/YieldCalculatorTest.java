package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class YieldCalculatorTest {
    @Test
    void 수익률_계산_테스트() {
        Assertions.assertThat(YieldCalculator.calculate(5000, 14000))
                .isEqualTo(0.35);
    }
}
