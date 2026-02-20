package com.kakao.onboarding.precourse.albusduke.lotto.util;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseGameAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.Statistics;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoGames;

public interface Output {

    void outputPurchaseGameAmount(PurchaseGameAmount purchasedGameAmount);

    void outputManualLottoNumbersPrompt();

    void outputPurchaseCount(int manualCount, int autoCount);

    void outputLottoNumbers(LottoGames lottoGames);

    void outputStatistics(Statistics statistics);

    void outputError(IllegalArgumentException e);
}
