package com.lotto;

import com.lotto.model.LottoTickets;
import com.lotto.util.LottoNumberGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    @Test
    void 구입_금액에_해당하는_로또를_구매할_수_있다() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        LottoTickets lottoTickets = new LottoTickets(14, lottoNumberGenerator);

        assertThat(lottoTickets.getLottoTickets()).hasSize(14);
    }
}
