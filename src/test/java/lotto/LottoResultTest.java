package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoResultTest {

    @Test
    void 수익률_계산_테스트_5등_1개() {
        List<Rank> ranks = Arrays.asList(Rank.FIFTH); // 5,000원
        LottoResult lottoResult = new LottoResult(ranks);
        Money purchaseMoney = new Money(5000);

        double yield = lottoResult.calculateYield(purchaseMoney);

        Assertions.assertThat(yield).isEqualTo(1.0);
    }

    @Test
    void 수익률_계산_테스트_꽝_여러개() {
        List<Rank> ranks = Arrays.asList(Rank.MISS, Rank.MISS, Rank.MISS);
        LottoResult lottoResult = new LottoResult(ranks);
        Money purchaseMoney = new Money(3000);

        double yield = lottoResult.calculateYield(purchaseMoney);

        Assertions.assertThat(yield).isEqualTo(0.0);
    }

    @Test
    void 수익률_계산_테스트_복합() {
        List<Rank> ranks = Arrays.asList(
                Rank.FIFTH,
                Rank.MISS, Rank.MISS, Rank.MISS, Rank.MISS);
        LottoResult lottoResult = new LottoResult(ranks);
        Money purchaseMoney = new Money(14000);

        double yield = lottoResult.calculateYield(purchaseMoney);

        Assertions.assertThat(yield).isEqualTo(0.35);
    }
}
