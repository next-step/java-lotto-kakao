package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {

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

    Lotto lotto0, lotto1;
    List<Lotto> lottos;
    LottoResult lottoResult;

    @BeforeEach
    void beforeTest() {
        lotto0 = new Lotto(new LottoNumbers(
                Arrays.asList(number6, number5, number4, number3, number2, number1)
        ));
        lotto1 = new Lotto(new LottoNumbers(
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

        lottoResult = new LottoResult(
                new LottoNumbers(Arrays.asList(number1, number2, number3, number4, number5, number6)),
                new LottoNumber(7)
        );
    }

    // 로또의 번호 순서와 관계없이 같은 번호 조합이면 같은 넘버마스크 값을 갖는다.
    @Test
    void getNumberMaskTest() {
        assertThat(lotto1.getNumberMask()).isEqualTo(lotto0.getNumberMask());
    }

    // 로또의 번호와 로또 결과에 따라 랭크가 결정된다.
    @Test
    void getRankTest() {
        Rank[] ranks = {
                Rank.FIRST,
                Rank.SECOND,
                Rank.THIRD,
                Rank.FOURTH,
                Rank.FIFTH,
                Rank.LOSER,
        };
        for (int i = 0; i < 6; i++) {
            assertThat(lottos.get(i).getRank(lottoResult)).isEqualTo(ranks[i]);
        }
    }
}
