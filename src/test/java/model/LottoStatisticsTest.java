package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoStatisticsTest {

    LottoNumber number1 = LottoNumber.of(1);
    LottoNumber number2 = LottoNumber.of(2);
    LottoNumber number3 = LottoNumber.of(3);
    LottoNumber number4 = LottoNumber.of(4);
    LottoNumber number5 = LottoNumber.of(5);
    LottoNumber number6 = LottoNumber.of(6);
    LottoNumber number7 = LottoNumber.of(7);
    LottoNumber number8 = LottoNumber.of(8);
    LottoNumber number9 = LottoNumber.of(9);
    LottoNumber number10 = LottoNumber.of(10);

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
                number7
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
