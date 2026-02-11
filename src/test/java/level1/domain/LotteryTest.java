package level1.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LotteryTest {

    @Test
    @DisplayName("중복된 번호는 허용되지 않는다.")
    void testDuplicateLottery() {
        assertThatThrownBy(() -> new Lottery(List.of("1", "2", "3", "4", "5", "1")))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("로또 번호는 1 - 45 범위 숫자만 허용된다.")
    void testOutOfRange() {
        assertThatThrownBy(() -> new Lottery(List.of("1", "2", "3", "4", "5", "46")))
                .isInstanceOf(RuntimeException.class);

        assertThatThrownBy(() -> new Lottery(List.of("1", "2", "3", "4", "5", "-6")))
                .isInstanceOf(RuntimeException.class);

        assertThatThrownBy(() -> new Lottery(List.of("0", "2", "3", "4", "5", "6")))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("숫자가 아닌 값은 허용되지 않는다.")
    void testInvalidInteger() {
        assertThatThrownBy(() -> new Lottery(List.of("1", "2", "3", "4", "5", "a")))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("로또 숫자는 6개여야 한다.")
    void testInsufficientLottery() {
        assertThatThrownBy(() -> new Lottery(List.of("1", "2", "3", "4", "5")))
                .isInstanceOf(RuntimeException.class);
    }
}
