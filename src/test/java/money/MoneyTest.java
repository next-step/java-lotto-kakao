package money;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void createFailBelowRange() {
        assertThrows(IllegalArgumentException.class,
                () -> new Money(0));
    }
}
