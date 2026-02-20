import domains.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStoreTest {
    private LottoStore lottoStore;

    @BeforeEach
    void setUp() {
        lottoStore = new LottoStore();
    }

    @Test
    void 수동_로또와_남은_금액만큼의_자동_로또를_합쳐서_구매한다() {
        // given
        ManualLottoGenerator manualGenerator = new ManualLottoGenerator();
        manualGenerator.addManualNumbers(List.of(1, 2, 3, 4, 5, 6));
        manualGenerator.addManualNumbers(List.of(7, 8, 9, 10, 11, 12));

        AutoLottoGenerator autoGenerator = new AutoLottoGenerator(3); // 자동 3장

        LottoTickets ticket = lottoStore.buy(manualGenerator, autoGenerator);

        assertThat(ticket.lottos()).hasSize(5);

        Lotto manual1 = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto manual2 = new Lotto(7, 8, 9, 10, 11, 12);
        assertThat(ticket.lottos()).contains(manual1, manual2);
    }
}