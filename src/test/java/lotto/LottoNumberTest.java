package lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {

    @Test
    void createSuccessInRange() {
        assertDoesNotThrow(() -> new LottoNumber(1));
        assertDoesNotThrow(() -> new LottoNumber(45));
    }

    @Test
    void createFailBelowRange() {
        assertThrows(IllegalArgumentException.class,
                () -> new LottoNumber(0));
    }

    @Test
    void createFailAboveRange() {
        assertThrows(IllegalArgumentException.class,
                () -> new LottoNumber(46));
    }

    @Test
    void createFromCache() {
        LottoNumber first = LottoNumber.from(7);
        LottoNumber second = LottoNumber.from(7);

        assertSame(first, second);
    }
}
