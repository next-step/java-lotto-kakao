package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoResultCalculatorTest {

    private LottoPlayer lottoPlayer;

    @BeforeEach
    void beforeEach() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        Lotto lotto3 = new Lotto(List.of(13, 14, 15, 16, 17, 18));
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3);

        this.lottoPlayer = new LottoPlayer(3000, 3, lottos);
    }

    @Test
    @DisplayName("성공케이스")
    void success() {
        WinningLotto winningLottoNumber = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        LottoResultCalculator calculator = new LottoResultCalculator(lottoPlayer, winningLottoNumber);

        LottoResult result = calculator.calculate();
        long profit = result.getProfit();
        double profitRate = result.getProfitRate();

        assertThat(result.getStatuses().get(LottoStatus.SIX_CORRECT)).isEqualTo(1);
        assertThat(profit).isEqualTo(2000000000);
        assertThat(profitRate).isEqualTo(666666.6666666666);
    }
}
