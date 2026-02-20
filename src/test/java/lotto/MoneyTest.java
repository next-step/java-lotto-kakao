package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("1500원 단위 입력 예외처리")
    void 단위_예외_테스트() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(1500))
                .withMessage("천원 단위로만 입력이 가능합니다.");
    }

    @Test
    void 음수_돈_예외_테스트() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(-1000))
                .withMessage("양수로만 입력이 가능합니다.");
    }

    @Test
    void 로또_개수_계산_테스트() {
        Money money = new Money(5000);
        Assertions.assertThat(money.calculateLottoCount()).isEqualTo(5);
    }

    @Test
    void zero_메소드_테스트() {
        Money zero = Money.zero();
        Assertions.assertThat(zero.money()).isEqualTo(0);
    }

    @Test
    void sum_메소드_테스트() {
        Money money = new Money(1000);
        Money other = new Money(2000);
        Assertions.assertThat(money.sum(other).money()).isEqualTo(3000);
    }
}
