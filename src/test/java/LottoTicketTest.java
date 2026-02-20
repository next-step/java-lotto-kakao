import domains.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {
    @Test
    void 당첨_번호와_비교하여_올바른_등수_결과를_반환한다() {
        // 1등 당첨 로또 (1, 2, 3, 4, 5, 6)
        Lotto firstPlaceLotto =  new Lotto(1, 2, 3, 4, 5, 6);
        // 5등 당첨 로또 (1, 2, 3 일치)
        Lotto fifthPlaceLotto =  new Lotto(1, 2, 3, 11, 12, 13);
        // 꽝 로또
        Lotto missLotto =  new Lotto(11, 12, 13, 14, 15, 16);

        LottoTickets tickets = new LottoTickets(List.of(firstPlaceLotto, fifthPlaceLotto, missLotto));

        // 당첨 번호: 1, 2, 3, 4, 5, 6 / 보너스: 7
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(1, 2, 3, 4, 5, 6),
                new LottoNumber(7)
        );

        List<Rank> results = tickets.match(winningLotto);

        // 순서대로 1등, 5등, 꽝(MISS)이 나와야 함
        assertThat(results).hasSize(3)
                .containsExactly(Rank.FIRST, Rank.FIFTH, Rank.MISS);
    }
}