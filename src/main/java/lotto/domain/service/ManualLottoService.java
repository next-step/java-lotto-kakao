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

    //    @Override
//    public LottoPlayer createPlayer() {
//        Money price = readPrice();
//        LottoCount totalCount = price.toLottoCount(ONE_LOTTO_PRICE);
//        LottoCount manualCount = readManualCount(totalCount);
//        LottoCount autoCount = totalCount.minus(manualCount);
//
//        outputView.printManualLottoRequest();
//
//        Lottos manualLottos = buyLottos(manualCount, manualPickStrategy);
//        Lottos autoLottos = buyLottos(autoCount, autoPickStrategy);
//        outputView.printManualBuyResult(manualCount.value(), totalCount.value());
//
//        Lottos all = Lottos.merge(manualLottos, autoLottos);
//        outputView.printLottos(all);
//
//        return LottoPlayer.of(price, all);
//    }
//
//    private Money readPrice() {
//        outputView.printPriceRequest();
//        return Money.won(inputView.inputNumber());
//    }
//
//    private LottoCount readManualCount(LottoCount totalCount) {
//        outputView.printManualCountRequest();
//        LottoCount manualCount = LottoCount.manual(inputView.inputNumber());
//        manualCount.validateNotExceeded(totalCount);
//        return manualCount;
//    }
//
//    private Lottos buyLottos(LottoCount count, LottoPickStrategy strategy) {
//        List<Lotto> lottos = new ArrayList<>();
//        for (int i = 0; i < count.value(); i++) {
//            lottos.add(new Lotto(strategy.generate()));
//        }
//        return Lottos.from(lottos);
//    }
}

