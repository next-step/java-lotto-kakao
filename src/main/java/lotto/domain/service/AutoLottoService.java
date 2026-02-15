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
import java.util.List;

public class AutoLottoService implements LottoService {

    private final LottoPickStrategy autoPickStrategy;
    private final InputView inputView;
    private final OutputView outputView;

    public AutoLottoService(
            final LottoPickStrategy autoPickStrategy,
            final InputView inputView,
            final OutputView outputView
    ) {
        this.autoPickStrategy = autoPickStrategy;
        this.inputView = inputView;
        this.outputView = outputView;
    }


    @Override
    public LottoPlayer createPlayer() {
        Money price = readPrice();
        LottoCount lottoCount = price.toLottoCount(ONE_LOTTO_PRICE);

        outputView.printAutoBuyResult(lottoCount.value());

        Lottos lottos = buyLottos(lottoCount);
        outputView.printLottos(lottos);

        return LottoPlayer.of(price, lottos);
    }

    private Money readPrice() {
        outputView.printPriceRequest();
        return Money.won(inputView.inputNumber());
    }

    private Lottos buyLottos(LottoCount count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count.value(); i++) {
            lottos.add(new Lotto(autoPickStrategy.generate()));
        }
        return Lottos.from(lottos);
    }
}
