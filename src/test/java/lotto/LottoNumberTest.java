package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void cacheTest() {
        for (int i = 1; i <= 45; i++) {
            LottoNumber first = LottoNumber.of(i);
            LottoNumber second = LottoNumber.of(i);
            assertThat(first).isSameAs(second);
        }
    }

    @Test
    void createSuccessInRange() {
        assertDoesNotThrow(() -> LottoNumber.of(1));
        assertDoesNotThrow(() -> LottoNumber.of(45));
    }

    @Test
    void createFailBelowRange() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoNumber.of(0));
    }

    @Test
    void createFailAboveRange() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoNumber.of(46));
    }
}
