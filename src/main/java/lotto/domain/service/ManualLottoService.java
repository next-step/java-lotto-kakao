package lotto.domain.service;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoPlayer;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.pick.LottoPickStrategy;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoService implements LottoService {

    private final LottoPickStrategy autoPickStrategy;

    public ManualLottoService(final LottoPickStrategy autoPickStrategy) {
        this.autoPickStrategy = autoPickStrategy;
    }

    @Override
    public LottoPlayer purchase(Money price, List<Lotto> manualLottos) {
        LottoCount totalCount = price.toLottoCount(Money.ONE_LOTTO_PRICE);
        LottoCount manualCount = LottoCount.manual(manualLottos.size());

        manualCount.validateNotExceeded(totalCount);

        LottoCount autoCount = totalCount.minus(manualCount);

        Lottos manual = Lottos.from(manualLottos);
        Lottos auto = generate(autoCount);

        Lottos merged = manual.merge(auto);

        return LottoPlayer.of(price, merged);
    }

    private static Lottos getManual(Lottos manual) {
        return manual;
    }

    private Lottos generate(LottoCount count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count.value(); i++) {
            lottos.add(Lotto.fromNumbers(autoPickStrategy.generate()));
        }
        return Lottos.from(lottos);
    }
}

