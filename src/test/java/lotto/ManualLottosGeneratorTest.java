package lotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManualLottosGeneratorTest {

    @Test
    void ManualLottosContainNull() {
        List<Lotto> manualLottos = new ArrayList<>();
        manualLottos.add(null);

        assertThrows(IllegalArgumentException.class,
                () -> new ManualLottosGenerator(manualLottos));
    }

    @Test
    void ManualLottosIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new ManualLottosGenerator(null));
    }
}
