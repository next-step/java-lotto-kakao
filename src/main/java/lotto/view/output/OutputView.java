package lotto.view.output;

import lotto.domain.LottoStatus;
import lotto.domain.Lottos;

import java.util.Map;

public interface OutputView {

    void printMessage(String message);

    void printLottos(Lottos lottos);

    void printWinningStatistics(
            final Map<LottoStatus, Integer> statuses,
            double profitRate
    );

    void printManualCountRequest();

    void printManualLottoRequest();

    void printAutoNumberRequest();

    void printPriceRequest();

    void printAutoBuyResult(int count);

    void printManualBuyResult(int manualCount, int totalCount);

    void printLastWeekWinningNumberRequest();

    void printBonusNumberRequest();


}
