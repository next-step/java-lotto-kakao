package lotto.domain.service;

import lotto.domain.Lotto;
import lotto.domain.LottoPlayer;
import lotto.domain.Lottos;
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
        int price = readPrice();
        int lottoCount = countFrom(price);

        outputView.printAutoBuyResult(lottoCount);

        Lottos lottos = buyLottos(lottoCount);
        outputView.printLottos(lottos);

        return LottoPlayer.of(price, lottos);
    }

    private int readPrice() {
        outputView.printPriceRequest();
        int price = inputView.inputNumber();
        validatePrice(price);
        return price;
    }

    private void validatePrice(int price) {
        if (price < ONE_LOTTO_PRICE) {
            throw new IllegalArgumentException(PRICE_NOT_ENOUGH_MSG);
        }
    }

    private int countFrom(int price) {
        return price / LottoService.ONE_LOTTO_PRICE;
    }

    private Lottos buyLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(autoPickStrategy.generate()));
        }
        return Lottos.from(lottos);
    }
}
