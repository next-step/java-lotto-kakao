package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        Money purchaseAmount = inputView.inputMoney();
        Wallet wallet = new Wallet(purchaseAmount);

        AutoMachine autoMachine = new AutoMachine();
        LottoTickets tickets = autoMachine.allIn(wallet);

        outputView.printPurchaseCount(tickets.size());
        outputView.printTickets(tickets);

        LottoTicket winningNumbers = inputView.inputWinningNumbers();
        LottoNumber bonusNumber = inputView.inputBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        WinningInfo winningInfo = tickets.result(winningLotto);
        outputView.printStatistics(winningInfo);

        Money totalPrize = winningInfo.getTotalPrice();
        double rateOfReturn = wallet.returnRate(totalPrize);
        outputView.printRateOfReturn(rateOfReturn);
    }

}
