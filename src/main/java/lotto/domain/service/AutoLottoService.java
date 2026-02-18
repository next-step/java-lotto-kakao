package lotto.domain.service;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoPlayer;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.pick.LottoPickStrategy;

import java.util.ArrayList;
import java.util.List;

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
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count.value(); i++) {
            lottos.add(Lotto.fromNumbers(autoPickStrategy.generate()));
        }
        return Lottos.from(lottos);
    }
}
