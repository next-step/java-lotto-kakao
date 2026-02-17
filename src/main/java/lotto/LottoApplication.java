package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoPlayer;
import lotto.domain.LottoStatus;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.domain.service.LottoService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoApplication {

    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoApplication(
            final LottoService lottoService,
            final InputView inputView,
            final OutputView outputView
    ) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void playManual() {
        Money price = readPrice();
        LottoCount totalCount = price.toLottoCount(Money.ONE_LOTTO_PRICE);
        LottoCount manualCount = readManualCount();

        manualCount.validateNotExceeded(totalCount);
        List<Lotto> manualLottos = readManualLottos(manualCount);
        LottoPlayer player = lottoService.purchase(price, manualLottos);

        outputView.printManualBuyResult(manualCount.value(), totalCount.value());
        outputView.printLottos(player.getLottos());

        finishGame(player);
    }

    public void playAuto() {
        Money price = readPrice();

        LottoPlayer player = lottoService.purchase(price, List.of());
        LottoCount totalCount = price.toLottoCount(Money.ONE_LOTTO_PRICE);
        outputView.printAutoBuyResult(totalCount.value());
        outputView.printLottos(player.getLottos());

        finishGame(player);
    }

    private void finishGame(LottoPlayer player) {
        WinningLotto winningLotto = readWinningLotto();
        Map<LottoStatus, Integer> result = winningLotto.countByStatus(player.getLottos());

        long totalPrize = LottoStatus.totalPrize(result);
        double profitRate = (double) totalPrize / player.getPrice().getAmount();

        outputView.printWinningStatistics(result, profitRate);
    }

    private Money readPrice() {
        outputView.printPriceRequest();
        return Money.won(inputView.inputNumber());
    }

    private LottoCount readManualCount() {
        outputView.printManualCountRequest();
        return LottoCount.of(inputView.inputNumber());
    }

    private List<Lotto> readManualLottos(LottoCount manualCount) {
        outputView.printManualLottoRequest();

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < manualCount.value(); i++) {
            lottos.add(Lotto.fromIntegers(inputView.inputNumbers(",")));
        }

        return lottos;
    }

    private WinningLotto readWinningLotto() {
        outputView.printLastWeekWinningNumberRequest();
        List<Integer> winningNumbers = inputView.inputNumbers(",");

        outputView.printBonusNumberRequest();
        int bonus = Integer.parseInt(inputView.input());

        return WinningLotto.of(Lotto.fromIntegers(winningNumbers), bonus);
    }
}
