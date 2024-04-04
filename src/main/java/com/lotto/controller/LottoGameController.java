package com.lotto.controller;

import com.lotto.model.LottoGame;
import com.lotto.model.LottoResults;
import com.lotto.util.LottoNumberGenerator;
import com.lotto.view.LottoGameInputView;
import com.lotto.view.LottoGameOutputView;

import java.util.List;

public class LottoGameController {
    private final LottoGameInputView lottoGameInputView;
    private final LottoGameOutputView lottoGameOutputView;

    public LottoGameController(LottoGameInputView lottoGameInputView, LottoGameOutputView lottoGameOutputView) {
        this.lottoGameInputView = lottoGameInputView;
        this.lottoGameOutputView = lottoGameOutputView;
    }

    public void run() {
        LottoGame lottoGame = inputMoneyAndCreateLottoGame();
        lottoGameOutputView.printLottoTicketSize(lottoGame.getLottoTicketSize());
        lottoGameOutputView.printLottoTicketList(lottoGame);

        List<Integer> winningNumbers = lottoGameInputView.inputWinningNumbers();
        int bonusNumber = lottoGameInputView.inputBonusNumber();
        LottoResults lottoResults = lottoGame.play(winningNumbers, bonusNumber);

        lottoGameOutputView.printLottoResults(lottoResults);
        lottoGameOutputView.printProfitRate(lottoGame);
    }

    private LottoGame inputMoneyAndCreateLottoGame() {
        int money = lottoGameInputView.inputMoney();
        return new LottoGame(money, new LottoNumberGenerator());
    }
}
