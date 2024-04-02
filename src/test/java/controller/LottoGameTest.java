package controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoGame 관련 테스트")
class LottoGameTest {
    @Test
    void 수익률을_확인() {
        LottoGame game = LottoGame.of(
                6000,
                List.of(4, 5, 6, 7, 8, 9), 42,
                () -> List.of(1, 2, 3, 4, 5, 6)
        );
        game.start();
        double rate = game.profitRate();
        assertThat(rate).isEqualTo((double) (5_000) * 6 / 6000);
    }
}