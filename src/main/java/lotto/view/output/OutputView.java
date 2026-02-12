package lotto.view.output;

import lotto.domain.Lotto;
import lotto.domain.LottoStatus;

import java.util.List;
import java.util.Map;

public interface OutputView {

    public void printMessage(String message);

    public void printLottos(List<Lotto> lottos);

    public void printWinningStatistics(
            final Map<LottoStatus, Integer> statuses,
            double profitRate
    );

}
