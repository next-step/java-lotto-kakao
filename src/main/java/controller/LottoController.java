package controller;

import domain.lotto.LottoGroup;
import domain.lotto.Purchase;
import domain.winning.LottoResult;
import domain.winning.WinningLotto;
import service.LottoService;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = new LottoService();
    }

    public void run() {
        LottoGroup lottoGroup = generateLottoGroup();
        outputView.printLottoCount(lottoGroup);
        WinningLotto winningLotto = requestWinningNumbers();

        LottoResult lottoResult = lottoGroup.compare(winningLotto);
        outputView.printStatistics(lottoResult);
    }

    private LottoGroup generateLottoGroup() {
        try {
            return lottoService.issue(purchase());
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return generateLottoGroup();
        }
    }

    private Purchase purchase() {
        int purchasePrice = inputView.enterPurchasePrice();
        int manualLottoCount = inputView.enterManualLottoCount();
        List<List<Integer>> manualLottosNumbers = inputView.enterManualLottosNumbers(manualLottoCount);
        return new Purchase(purchasePrice, manualLottosNumbers);
    }

    private WinningLotto requestWinningNumbers() {
        try {
            List<Integer> winningNumbers = inputView.enterWinningNumbers();
            int bonusNumber = inputView.enterBonusNumber();
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return requestWinningNumbers();
        }
    }

}
