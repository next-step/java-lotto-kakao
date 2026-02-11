package domain.lotto;

import domain.winning.LottoResult;
import domain.winning.WinningLotto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

public class LottoResultTest {

    @Test
    public void dummy() {
        Random ramdom = new Random(10);
        LottoFactory lottoFactory = new LottoFactory(ramdom);
        LottoIssuer lottoIssuer = new LottoIssuer(lottoFactory);
        LottoGroup lottoGroup = lottoIssuer.issueAuto(3000);

        // seed 10
        // [16, 24, 26, 30, 31, 35]
        // [1, 2, 3, 29, 40, 45]
        // [6, 21, 23, 35, 37, 41]

        List<Integer> expectedFirstLotto = List.of(6,21,23,24,37,41);
        WinningLotto winningLotto = new WinningLotto(expectedFirstLotto, 35);
        LottoResult lottoResult = lottoGroup.compare(winningLotto);

        double rate = lottoResult.totalRate();
        Assertions.assertEquals(10000.0, rate);
    }
}
