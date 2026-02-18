package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoStatisticsTest {

    LottoNumber number1 = new LottoNumber(1);
    LottoNumber number2 = new LottoNumber(2);
    LottoNumber number3 = new LottoNumber(3);
    LottoNumber number4 = new LottoNumber(4);
    LottoNumber number5 = new LottoNumber(5);
    LottoNumber number6 = new LottoNumber(6);
    LottoNumber number7 = new LottoNumber(7);
    LottoNumber number8 = new LottoNumber(8);
    LottoNumber number9 = new LottoNumber(9);
    LottoNumber number10 = new LottoNumber(10);

    LottoStatistics lottoStatistics;

    @BeforeEach
    void beforeTest() {
        Lotto lotto1 = new Lotto(new LottoNumbers(
                Arrays.asList(number1, number2, number3, number4, number5, number6)
        ));
        Lotto lotto2 = new Lotto(new LottoNumbers(
                Arrays.asList(number1, number2, number3, number4, number5, number7)
        ));
        Lotto lotto3 = new Lotto(new LottoNumbers(
                Arrays.asList(number1, number2, number3, number4, number5, number8)
        ));
        Lotto lotto4 = new Lotto(new LottoNumbers(
                Arrays.asList(number1, number2, number3, number4, number8, number9)
        ));
        Lotto lotto5 = new Lotto(new LottoNumbers(
                Arrays.asList(number1, number2, number3, number8, number9, number10)
        ));
        Lotto lotto6 = new Lotto(new LottoNumbers(
                Arrays.asList(number1, number2, number7, number8, number9, number10)
        ));
        List<Lotto> lottos = Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6);
        LottoResult lottoResult = new LottoResult(
                new LottoNumbers(Arrays.asList(number1, number2, number3, number4, number5, number6)),
                new LottoNumber(7)
        );
        lottoStatistics = new LottoStatistics(lottos, lottoResult);
    }

    // 로또 통계에는 당첨결과가 알맞게 기록되어야 한다.
    @Test
    void getRankCountTest() {
        Rank[] ranks = {
                Rank.FIRST,
                Rank.SECOND,
                Rank.THIRD,
                Rank.FOURTH,
                Rank.FIFTH,
                Rank.LOSER,
        };
        for (Rank rank : ranks) {
            assertThat(lottoStatistics.getRankCount(rank)).isEqualTo(1);
        }
    }

    // 로또 통계에서는 정확한 수익률을 반환해야 한다.
    @Test
    void getProfitRatesTest() {
        assertThat(lottoStatistics.getProfitRates()).isEqualTo(338592.5);
    }
}
