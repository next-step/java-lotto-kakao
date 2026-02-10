package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottosResultCalculatorTest {

    private LottoPlayer lottoPlayer;

    @BeforeEach
    void beforeEach() {
        Lottos lottos1 = new Lottos(List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos2 = new Lottos(List.of(7, 8, 9, 10, 11, 12));
        Lottos lottos3 = new Lottos(List.of(13, 14, 15, 16, 17, 18));
        List<Lottos> lottos = List.of(lottos1, lottos2, lottos3);

        this.lottoPlayer = new LottoPlayer(3000, 3, lottos);
    }

    @Test
    @DisplayName("성공케이스")
    void success() {
        WinningLotto winningLottoNumber = new WinningLotto(new Lottos(List.of(1, 2, 3, 4, 5, 6)), 7);
        LottoResultCalculator calculator = new LottoResultCalculator(lottoPlayer, winningLottoNumber);

        LottoResult result = calculator.calculate();
        long profit = result.getProfit();
        double profitRate = result.getProfitRate();

        assertThat(result.getStatuses().get(LottoStatus.SIX_CORRECT)).isEqualTo(1);
        assertThat(profit).isEqualTo(2000000000);
        assertThat(profitRate).isEqualTo(666666.6666666666);
    }
}
