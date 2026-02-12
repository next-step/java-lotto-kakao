package lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchasedCountTest {
    @Test
    void 입력받은_수동개수_전체개수보다_작은지_예외_테스트(){
        int manual = 5;
        int total = 4;
        assertThrows(IllegalArgumentException.class,
                () -> new PurchasedCount(manual, total));
    }
}
