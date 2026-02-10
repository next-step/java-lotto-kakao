package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoPlayerTest {

    @Test
    @DisplayName("정상 입력 케이스")
    void successTest() {
        int price = 3000;
        int lottoCount = 3;
        List<Lotto> lottos = new ArrayList<>(
                List.of(
                        new Lotto(List.of(1,2,3,4,5,6)),
                        new Lotto(List.of(1,2,3,4,5,6)),
                        new Lotto(List.of(1,2,3,4,5,6))
                )
        );

        LottoPlayer lottoPlayer = new LottoPlayer(price, lottoCount, lottos);

        assertThat(lottoPlayer.getPrice()).isEqualTo(3000);
        assertThat(lottoPlayer.getLottoCount()).isEqualTo(3);
        assertThat(lottoPlayer.getLottos()).hasSize(3);
    }

    @Test
    @DisplayName("1000원 미만의 입력을 받은 경우")
    void fail_priceRange() {
        assertThatThrownBy(() -> {
            int price = 900;
            int lottoCount = 0;
            LottoPlayer lottoPlayer = new LottoPlayer(price, lottoCount, new ArrayList<>());
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1000원 미만의 구매 금액이 입력되었습니다.");
    }

}
