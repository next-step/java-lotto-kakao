package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class YieldCalculatorTest {
    @Test
    void 수익률_계산_테스트() {
        Assertions.assertThat(YieldCalculator.calculate(new Money(5000), new Money(14000)))
                .isEqualTo(0.35);
    }

    @Test
    void 수익률_0_테스트() {
        Assertions.assertThat(YieldCalculator.calculate(new Money(0), new Money(10000)))
                .isEqualTo(0.0);
    }

    @Test
    void 수익률_100퍼센트_테스트() {
        Assertions.assertThat(YieldCalculator.calculate(new Money(10000), new Money(10000)))
                .isEqualTo(1.0);
    }

    @Test
    void 수익률_초과_테스트() {
        Assertions.assertThat(YieldCalculator.calculate(new Money(20000), new Money(10000)))
                .isEqualTo(2.0);
    }

    @Test
    void 분모가_0일때_0반환_테스트() {
        Assertions.assertThat(YieldCalculator.calculate(new Money(10000), new Money(0)))
                .isEqualTo(0.0);
    }
}
