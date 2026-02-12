package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    GameScore gameScore = new GameScore(7, Arrays.asList(1, 2, 3, 4, 5, 6));
    List<Lotto> lottos;

    @BeforeEach
    void beforeTest() {
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
        lottos = Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6);
    }

    @Test
    void ticketLevel() {
        Rank[] ranks = {
                Rank.FIRST,
                Rank.SECOND,
                Rank.THIRD,
                Rank.FOURTH,
                Rank.FIFTH,
                Rank.LOSER,
        };

        for (int i = 0; i < 6; i++) {
            assertThat(lottos.get(i).getRank(gameScore)).isEqualTo(ranks[i]);
        }
    }

    @Test
    void profitRatioMaker() {
        StatsBoard statsBoard = new StatsBoard(gameScore, lottos);
        double profitRatio = statsBoard.getProfitRatio();
        assertThat(profitRatio).isEqualTo(338592.5);
    }
}
