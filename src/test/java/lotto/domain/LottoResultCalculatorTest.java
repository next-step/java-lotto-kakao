package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        Map<LottoStatus, Integer> status = winningLotto.countByStatus(lottoPlayer.getLottos());
        long profit = LottoStatus.totalPrize(status);
        double profitRate = (double) profit / lottoPlayer.getPrice();

        assertThat(status.get(LottoStatus.SIX_CORRECT)).isEqualTo(1);
        assertThat(status.getOrDefault(LottoStatus.FIVE_CORRECT_BONUS, 0)).isEqualTo(0);
        assertThat(status.getOrDefault(LottoStatus.FIVE_CORRECT, 0)).isEqualTo(0);
        assertThat(status.getOrDefault(LottoStatus.FOUR_CORRECT, 0)).isEqualTo(0);
        assertThat(status.getOrDefault(LottoStatus.THREE_CORRECT, 0)).isEqualTo(0);
        assertThat(profit).isEqualTo(2_000_000_000L);
        assertThat(profitRate).isEqualTo(666_666.6666666666);
    }
}
