package lotto.domain.service;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoPlayer;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.pick.LottoPickStrategy;

import java.util.List;
import java.util.stream.IntStream;

public class AutoLottoService implements LottoService {

    private final LottoPickStrategy autoPickStrategy;

    public AutoLottoService(final LottoPickStrategy autoPickStrategy) {
        this.autoPickStrategy = autoPickStrategy;
    }

    @Override
    public LottoPlayer purchase(Money price, List<Lotto> manualLottos) {
        LottoCount total = price.toLottoCount(Money.ONE_LOTTO_PRICE);
        Lottos lottos = generate(total);

        return LottoPlayer.of(price, lottos);
    }

    private Lottos generate(LottoCount count) {
        List<Lotto> lottos = IntStream.range(0, count.value())
                .mapToObj(i -> Lotto.fromNumbers(autoPickStrategy.generate()))
                .toList();

        return Lottos.from(lottos);
    }
}
