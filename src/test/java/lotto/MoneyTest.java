package lotto;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import lotto.model.Money;

public class MoneyTest {
    @Test
    void createMoney() {
        Money money = new Money(5_000);
        assertThat(money.amount()).isEqualTo(5_000);
    }

    @Test
    void throwExceptionWhenIsZeroOrNegative() {
        assertThatThrownBy(() -> new Money(0))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Money(-1_000))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void throwExceptionWhenAmountIsNotThousandUnit() {
        assertThatThrownBy(() -> new Money(1500))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
