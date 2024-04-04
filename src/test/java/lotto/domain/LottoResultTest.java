package lotto.domain;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class LottoResultTest {

    @Test
    public void 회차_진행_통계_정보를_받아_수익률을_계산한다() throws Exception {
        // given
        Map<Prize, Long> result = new HashMap<>();
        result.put(Prize.NOTHING, 1L);
        result.put(Prize.FIFTH, 1L);
        result.put(Prize.FOURTH, 1L);
        result.put(Prize.THIRD, 1L);
        result.put(Prize.SECOND, 1L);
        result.put(Prize.FIRST, 1L);

        LottoResult lottoResult = new LottoResult(result);
        LottoPurchaseBudget budget = new LottoPurchaseBudget(6000);

        // when
        double rate = lottoResult.getProfitRate(budget);

        // then
        Assertions.assertThat(rate).isEqualTo(338592.5);
    }
}
