package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoPlayerTest {

    @Test
    @DisplayName("플레이어는 구매금액과 로또 묶음을 가진다")
    void successTest() {
        Money price = Money.won(3000);
        Lottos lottos = Lottos.from(List.of(
                new Lotto(1, 2, 3, 4, 5, 6),
                new Lotto(7, 8, 9, 10, 11, 12),
                new Lotto(13, 14, 15, 16, 17, 18)
        ));

        LottoPlayer lottoPlayer = LottoPlayer.of(price, lottos);

        assertThat(lottoPlayer.getPrice().value()).isEqualTo(3000);
        assertThat(lottoPlayer.getLottoCount().value()).isEqualTo(3);
        assertThat(lottoPlayer.getLottos().asList()).hasSize(3);
    }

    @Test
    @DisplayName("Money는 음수 금액을 허용하지 않는다")
    void fail_priceRange() {
        assertThatThrownBy(() -> Money.won(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Money.NEGATIVE_MONEY_MSG);
    }

}
