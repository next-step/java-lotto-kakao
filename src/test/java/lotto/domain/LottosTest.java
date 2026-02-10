package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottosTest {

    @Test
    @DisplayName("정상적으로 로또 번호를 입력한 경우")
    void lottoSuccessTest() {
        Lottos lottos = new Lottos(List.of(1, 2, 3, 4, 5, 6));

        Assertions.assertThat(lottos.getNumbers()).hasSize(6);
        Assertions.assertThat(lottos.getNumbers()).containsExactly(1,2,3,4,5,6);
    }

    @Test
    @DisplayName("1 ~ 45 범위를 벗어나는 숫자인 경우")
    void lottoFailTest1() {
        Assertions.assertThatThrownBy(() -> {
            Lottos lottos = new Lottos(List.of(1,2,3,4,5,99));
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("1 ~ 45 범위를 벗어나는 숫자가 입력되었습니다.");
    }

    @Test
    @DisplayName("중복된 숫자를 입력하는 경우")
    void lottoFailTest2() {
        Assertions.assertThatThrownBy(() -> {
            Lottos lottos = new Lottos(List.of(1,2,3,4,5,5));
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("중복된 숫자가 입력되었습니다.");
    }

    @Test
    @DisplayName("숫자가 6개가 아닌 경우")
    void lottoFailTest3() {
        Assertions.assertThatThrownBy(() -> {
            Lottos lottos = new Lottos(List.of(1,2,3,4,5,6,7));
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("입력된 숫자가 6개가 아닙니다.");
    }
}
