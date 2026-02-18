package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottosTest {

    // 구매금액이 1000원 단위가 아니면 로또를 구매할 수 없다.
    @Test
    void validateTest() {
        List<Integer> prices = Arrays.asList(-1000, 0, 2500);
        for (int price : prices) {
            assertThatThrownBy(() -> new Lottos(price))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // 구매금액에 맞게 로또가 발행되어야 한다.
    @Test
    void issueTest() {
        Lottos lottos = new Lottos(12000);
        assertThat(lottos.getLottos().size()).isEqualTo(12);
    }
}
