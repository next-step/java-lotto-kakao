package lotto.view.output;

import lotto.domain.LottoStatus;
import lotto.domain.Lottos;

import java.util.Map;

public interface OutputView {

    public void printMessage(String message);

    public void printLottos(Lottos lottos);

    public void printWinningStatistics(
            final Map<LottoStatus, Integer> statuses,
            double profitRate
    );

    public void printManualCountRequest();

    public void printManualLottoRequest();

    public void printAutoNumberRequest();

    public void printPriceRequest();

    public void printAutoBuyResult(int count);

    public void printManualBuyResult(int manualCount, int totalCount);

}
