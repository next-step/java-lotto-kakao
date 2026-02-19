package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoStatus;

import java.util.List;
import java.util.Map;

public interface OutputView {

    void printLog(List<LottoNumber> list);

    void printPriceMessage();

    void printLottoCountMessage(int manualLottoCount, int autoLottoCount);

    void printStatistics(Map<LottoStatus, Integer> statuses);

    void printProfitRate(double profitRate);

    void printWinningLottoMessage();

    void printBonusNumberMessage();

    void printManualLottoCount();

    void printManualLottoInputMessage();
}
