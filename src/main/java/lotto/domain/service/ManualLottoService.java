package lotto.domain.service;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoPlayer;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.pick.LottoPickStrategy;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManualLottoService implements LottoService {


    private final LottoPickStrategy autoPickStrategy;
    private final LottoPickStrategy manualPickStrategy;
    private final InputView inputView;
    private final OutputView outputView;

    public ManualLottoService(
            final LottoPickStrategy autoPickStrategy,
            final LottoPickStrategy manualPickStrategy,
            final InputView inputView,
            final OutputView outputView
    ) {
        this.autoPickStrategy = autoPickStrategy;
        this.manualPickStrategy = manualPickStrategy;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public LottoPlayer createPlayer() {
        Money price = readPrice();
        LottoCount totalCount = price.toLottoCount(ONE_LOTTO_PRICE);
        LottoCount manualCount = readManualCount(totalCount);
        LottoCount autoCount = totalCount.minus(manualCount);

        outputView.printManualLottoRequest();

        Lottos manualLottos = buyLottos(manualCount, manualPickStrategy);
        Lottos autoLottos = buyLottos(autoCount, autoPickStrategy);
        outputView.printManualBuyResult(manualCount.value(), totalCount.value());

        Lottos all = Lottos.merge(manualLottos, autoLottos);
        outputView.printLottos(all);

        return LottoPlayer.of(price, all);
    }

    private Money readPrice() {
        outputView.printPriceRequest();
        return Money.won(inputView.inputNumber());
    }

    private LottoCount readManualCount(LottoCount totalCount) {
        outputView.printManualCountRequest();
        LottoCount manualCount = LottoCount.manual(inputView.inputNumber());
        manualCount.validateNotExceeded(totalCount);
        return manualCount;
    }

    private Lottos buyLottos(LottoCount count, LottoPickStrategy strategy) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count.value(); i++) {
            lottos.add(createSortedLotto(strategy));
        }
        return Lottos.from(lottos);
    }

    private Lotto createSortedLotto(LottoPickStrategy strategy) {
        List<Integer> numbers = new ArrayList<>(strategy.generate());
        Collections.sort(numbers);
        return Lotto.fromIntegers(numbers);
    }



}
