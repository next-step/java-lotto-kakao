package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("성공 케이스")
    void success() {
        Price price = new Price(3000);
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        );

        User user = new User(price, lottos, 1);

        assertThat(user.getPrice()).isEqualTo(3000);
        assertThat(user.getLottoCount()).isEqualTo(3);
        assertThat(user.getManualLottoCount()).isEqualTo(1);
        assertThat(user.getAutoLottoCount()).isEqualTo(2);
        assertThat(user.getLottos()).hasSize(3);
    }

    @Test
    @DisplayName("로또 개수가 구매 금액과 일치하지 않으면 예외를 발생시킵니다.")
    void fail_lottoCountMismatchException() {
        assertThatThrownBy(() -> {
            Price price = new Price(3000);
            List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
            new User(price, lottos, 1);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(User.LOTTO_COUNT_MISMATCH_EXCEPTION);
    }

    @Test
    @DisplayName("구매할 수 있는 로또의 개수보다 많은 수동 로또 개수가 입력되면 예외를 발생시킵니다.")
    void fail_manualLottoCountExceedingException() {
        assertThatThrownBy(() -> {
            Price price = new Price(3000);
            List<Lotto> lottos = List.of(
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                    new Lotto(List.of(1, 2, 3, 4, 5, 6))
            );
            new User(price, lottos, 4);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(User.MANUAL_LOTTO_COUNT_EXCEEDING_EXCEPTION);
    }

    @Test
    @DisplayName("수동 로또 개수에 음수가 입력되면 예외를 발생시킵니다.")
    void fail_manualLottoCountNegativeException() {
        assertThatThrownBy(() -> {
            Price price = new Price(3000);
            List<Lotto> lottos = List.of(
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                    new Lotto(List.of(1, 2, 3, 4, 5, 6))
            );
            new User(price, lottos, -1);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(User.MANUAL_LOTTO_COUNT_NEGATIVE_EXCEPTION);
    }
}
