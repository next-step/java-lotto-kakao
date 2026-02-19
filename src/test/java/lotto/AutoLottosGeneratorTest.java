package lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AutoLottosGeneratorTest {

    @Test
    void AutoCountIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new AutoLottosGenerator(-1L, new LottoGenerator()));
    }

    @Test
    void GeneratorIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new AutoLottosGenerator(1L, null));
    }
}
