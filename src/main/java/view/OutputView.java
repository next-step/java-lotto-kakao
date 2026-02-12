package view;

import model.Lotto;
import model.LottoStatistics;
import model.Lottos;
import model.RankView;

public class OutputView {

    public void printInputPriceMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printLottos(Lottos lottos) {
        System.out.println(lottos.getLottos().size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }

    public void printInputMainNumbersMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printInputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printLottoStatisticsTitle() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printLottoStatisticsDetail(RankView rankView, LottoStatistics lottoStatistics) {
        System.out.printf("%s (%d원) - %d개\n",
                rankView.getDescription(),
                rankView.getRank().getPrice(),
                lottoStatistics.getRankCount(rankView.getRank()));
    }

    public void printLottoProfitRates(LottoStatistics lottoStatistics) {
        System.out.printf("총 수익률은 %.2f입니다.\n", lottoStatistics.getProfitRates());
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println("[Error] " + e.getMessage());
    }
}
