package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class YieldCalculatorTest {
    @Test
    void 수익률_계산_테스트() {
        Assertions.assertThat(YieldCalculator.calculate(new Money(5000), new Money(14000)))
                .isEqualTo(0.35);
    }
}
