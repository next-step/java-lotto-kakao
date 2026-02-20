package com.kakao.onboarding.precourse.albusduke.lotto.view;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseGameAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.Statistics;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoGames;
import com.kakao.onboarding.precourse.albusduke.lotto.util.Output;

public class OutputView {

    private final Output output;

    public OutputView(Output output) {
        this.output = output;
    }

    public void outputPurchaseGameAmount(PurchaseGameAmount purchasedGameAmount) {
        output.outputPurchaseGameAmount(purchasedGameAmount);
    }

    public void OutputManualLottoNumbersPrompt() {
        output.outputManualLottoNumbersPrompt();
    }

    public void outputPurchaseCount(int manualCount, int autoCount) {
        output.outputPurchaseCount(manualCount, autoCount);
    }

    public void outputLottoNumbers(LottoGames lottoGames) {
        output.outputLottoNumbers(lottoGames);
    }

    public void outputStatistics(Statistics statistics) {
        output.outputStatistics(statistics);
    }

    public void outputError(IllegalArgumentException e) {
        output.outputError(e);
    }
}
