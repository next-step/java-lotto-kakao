package lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinLottoTest {
    @Test
    void winLottoDuplicate() {
        assertThrows(IllegalArgumentException.class,
                () -> new WinLotto(2, 2, 3, 4, 5, 6, 7));
    }
}
