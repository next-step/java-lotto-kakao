package com.lotto;

import com.lotto.controller.LottoGameController;
import com.lotto.view.LottoGameInputView;
import com.lotto.view.LottoGameOutputView;

public class LottoMain {
    public static void main(String[] args) {
        LottoGameInputView lottoGameInputView = new LottoGameInputView();
        LottoGameOutputView lottoGameOutputView = new LottoGameOutputView();

        LottoGameController lottoGameController = new LottoGameController(lottoGameInputView, lottoGameOutputView);
        lottoGameController.run();
    }
}
