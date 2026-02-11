package lotto.view.output;

import lotto.domain.Lotto;
import lotto.domain.LottoStatus;

import java.util.List;
import java.util.Map;

import static lotto.domain.LottoStatus.*;

public class TerminalOutputView implements OutputView {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printLottos(List<Lotto> lottos) {
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : lottos) {
            sb.append(lotto).append("\n");
        }
        System.out.println(sb.toString());
    }

    public void printWinningStatistics(
            final Map<LottoStatus, Integer> statuses,
            double profitRate
    ) {
        printMessage("당첨 통계");
        printMessage("---------");

        printStatusLine(THREE_CORRECT, "3개 일치", statuses);
        printStatusLine(FOUR_CORRECT,  "4개 일치", statuses);
        printStatusLine(FIVE_CORRECT,  "5개 일치", statuses);
        printStatusLine(FIVE_CORRECT_BONUS, "5개 일치, 보너스 볼 일치", statuses);
        printStatusLine(SIX_CORRECT,   "6개 일치", statuses);

        printMessage("총 수익률은 " + String.format("%.2f입니다.", profitRate));
    }

    private void printStatusLine(LottoStatus status, String label, Map<LottoStatus, Integer> statuses) {
        int count = statuses.getOrDefault(status, 0);
        printMessage(label + " (" + status.getPrice() + "원) - " + count + "개");
    }
}
