package com.lotto.controller;

import com.lotto.model.LottoGame;
import com.lotto.model.LottoResults;
import com.lotto.model.TargetLotto;
import com.lotto.util.LottoNumberGenerator;
import com.lotto.view.LottoGameInputView;
import com.lotto.view.LottoGameOutputView;

public class LottoGameController {
    private final LottoGameInputView lottoGameInputView;
    private final LottoGameOutputView lottoGameOutputView;

    public LottoGameController(LottoGameInputView lottoGameInputView, LottoGameOutputView lottoGameOutputView) {
        this.lottoGameInputView = lottoGameInputView;
        this.lottoGameOutputView = lottoGameOutputView;
    }

    public void run() {
        int money = lottoGameInputView.inputMoney();
        LottoGame lottoGame = new LottoGame(money, new LottoNumberGenerator());
        lottoGameOutputView.printLottoCount(lottoGame.getLottoTickets().size());
        lottoGameOutputView.printLottoList(lottoGame);

        TargetLotto targetLotto = lottoGameInputView.inputTargetNumbers();
        LottoResults lottoResults = lottoGame.play(targetLotto);

        lottoGameOutputView.printResult(lottoResults);
        lottoGameOutputView.printProfitRate(lottoGame);
    }
}
