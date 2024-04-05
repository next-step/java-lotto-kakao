package lotto;

import lotto.model.LottoRank;
import lotto.model.LottoResult;
import lotto.model.PurchaseAmount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class LottoResultTest {
    @Test
    void 로또_Rank들과_구매금액을_받아서_생성한다() {
        List<LottoRank> ranks = List.of(
                LottoRank.FIRST,
                LottoRank.FIRST,
                LottoRank.SECOND,
                LottoRank.THIRD,
                LottoRank.FOURTH,
                LottoRank.FIFTH
        );

        Assertions.assertDoesNotThrow(() -> new LottoResult(ranks));
    }

    @Test
    void 수익률을_계산한다() {
        List<LottoRank> ranks = List.of(
                LottoRank.FIRST,
                LottoRank.FIRST,
                LottoRank.SECOND
        );
        LottoResult result = new LottoResult(ranks);
        assertThat(result.calculateReturnRate(new PurchaseAmount(10_000L))).isEqualTo(403_000L);
    }

    @Test
    void 로또_당첨_결과를_맵으로_만든다() {
        List<LottoRank> ranks = List.of(
                LottoRank.FIRST,
                LottoRank.FIRST,
                LottoRank.SECOND,
                LottoRank.THIRD,
                LottoRank.FOURTH,
                LottoRank.FIFTH
        );
        LottoResult result = new LottoResult(ranks);

        assertThat(result.makeLottoResultMap()).contains(
                entry(LottoRank.FIRST, 2),
                entry(LottoRank.SECOND, 1),
                entry(LottoRank.THIRD, 1),
                entry(LottoRank.FOURTH, 1),
                entry(LottoRank.FIFTH, 1)
        );
    }
}
