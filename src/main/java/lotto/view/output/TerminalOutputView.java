package lotto.view.output;

import lotto.domain.Lotto;
import lotto.domain.LottoStatus;
import lotto.domain.Lottos;

import java.util.List;
import java.util.Map;

import static lotto.domain.LottoStatus.*;

public class TerminalOutputView implements OutputView {

    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printLottos(Lottos lottos) {
        printLottos(lottos.asList());
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
        System.out.print(sb.toString());
    }

    @Override
    public void printManualCountRequest() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
    }

    @Override
    public void printManualLottoRequest() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }

    @Override
    public void printAutoNumberRequest() {
        System.out.println("\n자동으로 구매할 로또 수를 입력해 주세요.");
    }

    @Override
    public void printPriceRequest() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    @Override
    public void printAutoBuyResult(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    @Override
    public void printManualBuyResult(int manualCount, int totalCount) {
        StringBuilder sb = new StringBuilder();
        int autoCount = totalCount - manualCount;
        sb.append("\n")
                .append("수동으로 ").append(manualCount)
                .append("장, 자동으로 ").append(autoCount)
                .append("개를 구매했습니다.");

        System.out.println(sb.toString());
    }

    @Override
    public void printLastWeekWinningNumberRequest() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    @Override
    public void printBonusNumberRequest() {
        System.out.println("보너스 볼을 입력해 주세요.");
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
