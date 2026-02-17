package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoBundleTest {
    @Test
    void 입력된_개수만큼_생성_테스트() {
        LottoBundle lottoBundle = new LottoBundle(makeLottos());
        Assertions.assertThat(lottoBundle.size())
                .isEqualTo(3);
    }

    @Test
    void 로또_결과_생성_테스트() {
        LottoBundle lottoBundle = new LottoBundle(makeLottos());
        Lotto winningLottoNumbers = testSetLotto(1, 7);
        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, new LottoNumber(7));

        List<Rank> ranks = lottoBundle.makeLottoRanks(winningLotto);

        Assertions.assertThat(ranks).containsExactly(Rank.FIRST, Rank.MISS, Rank.MISS);
    }

    List<Lotto> makeLottos() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(testSetLotto(1, 7));
        lottos.add(testSetLotto(8, 14));
        lottos.add(testSetLotto(15, 21));
        return lottos;
    }

    Lotto testSetLotto(int start, int end) {
        return new Lotto(IntStream.range(start, end)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList()));
    }
}
