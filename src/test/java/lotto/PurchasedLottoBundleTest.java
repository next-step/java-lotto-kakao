package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PurchasedLottoBundleTest {

    @Test
    void 수동_자동_순서대로_순회() {
        Lotto manualFirst = makeLotto(1, 2, 3, 4, 5, 6);
        Lotto manualSecond = makeLotto(7, 8, 9, 10, 11, 12);
        Lotto autoFirst = makeLotto(13, 14, 15, 16, 17, 18);

        PurchasedLottoBundle purchasedLottoBundle = new PurchasedLottoBundle(
                new LottoBundle(List.of(manualFirst, manualSecond)),
                new LottoBundle(List.of(autoFirst))
        );

        List<Lotto> ordered = new ArrayList<>();
        purchasedLottoBundle.forEachLottoInOrder(ordered::add);

        Assertions.assertThat(ordered).containsExactly(manualFirst, manualSecond, autoFirst);
    }

    @Test
    void 수동이나_자동이_0개여도_순회() {
        Lotto autoOnly = makeLotto(1, 2, 3, 4, 5, 6);
        PurchasedLottoBundle purchasedLottoBundle = new PurchasedLottoBundle(
                new LottoBundle(List.of()),
                new LottoBundle(List.of(autoOnly))
        );

        List<Lotto> ordered = new ArrayList<>();
        purchasedLottoBundle.forEachLottoInOrder(ordered::add);

        Assertions.assertThat(ordered).containsExactly(autoOnly);
    }

    @Test
    void 수동과_자동_결과를_합쳐_결과() {
        WinningLotto winningLotto = new WinningLotto(
                makeLotto(1, 2, 3, 4, 5, 6),
                LottoNumber.from(7)
        );

        PurchasedLottoBundle purchasedLottoBundle = new PurchasedLottoBundle(
                new LottoBundle(List.of(
                        makeLotto(1, 2, 3, 4, 5, 6),
                        makeLotto(1, 2, 3, 4, 5, 7)
                )),
                new LottoBundle(List.of(
                        makeLotto(20, 21, 22, 23, 24, 25)
                ))
        );

        LottoResult lottoResult = purchasedLottoBundle.makeLottoResult(winningLotto);

        Assertions.assertThat(lottoResult.toCount(Rank.FIRST)).isEqualTo(1);
        Assertions.assertThat(lottoResult.toCount(Rank.SECOND)).isEqualTo(1);
        Assertions.assertThat(lottoResult.toCount(Rank.MISS)).isEqualTo(1);
    }

    private Lotto makeLotto(int... numbers) {
        return new Lotto(Arrays.stream(numbers)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList()));
    }
}
