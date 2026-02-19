package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoStore lottoStore;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoStore = new LottoStore();
    }

    public void run() {
        Money purchaseAmount = inputView.inputMoney();
        Wallet wallet = new Wallet(purchaseAmount);

        int manualCount = inputView.inputManualCount();
        lottoStore.validateManualCount(purchaseAmount, manualCount);

        LottoTickets manualTickets = lottoStore.buyManual(wallet, inputView.inputManualNumbers(manualCount));
        LottoTickets autoTickets = lottoStore.buyAutoAllIn(wallet);
        manualTickets.merge(autoTickets);

        outputView.printPurchaseCount(manualCount, autoTickets.size());
        outputView.printTickets(manualTickets);

        LottoTicket winningNumbers = inputView.inputWinningNumbers();
        LottoNumber bonusNumber = inputView.inputBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        WinningInfo winningInfo = manualTickets.result(winningLotto);
        outputView.printStatistics(winningInfo);

        Money totalPrize = winningInfo.getTotalPrice();
        double rateOfReturn = wallet.returnRate(totalPrize);
        outputView.printRateOfReturn(rateOfReturn);
    }

}
