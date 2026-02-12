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
        List<LottoStatus> order = List.of(
                THREE_CORRECT,
                FOUR_CORRECT,
                FIVE_CORRECT,
                FIVE_CORRECT_BONUS,
                SIX_CORRECT
        );

        StringBuilder sb = new StringBuilder();
        sb.append("당첨 통계").append("\n");
        sb.append("---------").append("\n");

        for (LottoStatus status : order) {
            int count = statuses.getOrDefault(status, 0);
            sb.append(labelOf(status))
                    .append(" (")
                    .append(status.getPrice())
                    .append("원) - ")
                    .append(count)
                    .append("개")
                    .append("\n");
        }

        sb.append("총 수익률은 ").append(String.format("%.2f입니다.", profitRate)).append("\n");
        System.out.print(sb);
    }


    private String labelOf(LottoStatus status) {
        if (status == THREE_CORRECT) return "3개 일치";
        if (status == FOUR_CORRECT) return "4개 일치";
        if (status == FIVE_CORRECT) return "5개 일치";
        if (status == FIVE_CORRECT_BONUS) return "5개 일치, 보너스 볼 일치";
        if (status == SIX_CORRECT) return "6개 일치";
        return "";
    }

    private void printStatusLine(LottoStatus status, String label, Map<LottoStatus, Integer> statuses) {
        int count = statuses.getOrDefault(status, 0);
        printMessage(label + " (" + status.getPrice() + "원) - " + count + "개");
    }
}
