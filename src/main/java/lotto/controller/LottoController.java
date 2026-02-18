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
        try {
            Wallet wallet = initializeWallet();
            LottoSeller seller = new LottoSeller();
            MyLotto myLotto = new MyLotto();

            int manualCount = purchaseManual(wallet, seller, myLotto);
            LottoTickets autoTickets = seller.sellAuto(wallet);
            myLotto.addTickets(autoTickets);

            printPurchaseResult(manualCount, autoTickets.size(), myLotto.getAllTickets());

            processWinning(myLotto, wallet);
        } catch (RuntimeException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private Wallet initializeWallet() {
        Money purchaseAmount = inputView.inputMoney();
        return new Wallet(purchaseAmount);
    }

    private int purchaseManual(Wallet wallet, LottoSeller seller, MyLotto myLotto) {
        int manualCount = inputView.inputManualTicketCount();
        seller.checkPurchasability(wallet, manualCount);

        LottoTickets manualTickets = inputView.inputManualTickets(manualCount);
        seller.sellManual(wallet, manualTickets);
        myLotto.addTickets(manualTickets);
        
        return manualCount;
    }

    private void printPurchaseResult(int manualCount, int autoCount, LottoTickets tickets) {
        outputView.printPurchaseCount(manualCount, autoCount);
        outputView.printTickets(tickets);
    }

    private void processWinning(MyLotto myLotto, Wallet wallet) {
        WinningLotto winningLotto = createWinningLotto();
        WinningInfo winningInfo = myLotto.calculateResult(winningLotto);
        outputView.printStatistics(winningInfo);

        Money totalPrize = winningInfo.getTotalPrice();
        double rateOfReturn = wallet.Settlement(totalPrize);
        outputView.printRateOfReturn(rateOfReturn);
    }

    private WinningLotto createWinningLotto() {
        LottoTicket winningNumbers = inputView.inputWinningNumbers();
        LottoNumber bonusNumber = inputView.inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }
}
