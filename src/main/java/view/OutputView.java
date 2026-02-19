package view;

import model.Lotto;
import model.LottoRank;
import model.Lottos;

import java.util.Map;

public class OutputView {
    public void printPurchaseSummary(int manualLottoCount, int autoLottoCount) {
        System.out.println("수동으로 " + manualLottoCount + "장, 자동으로 " + autoLottoCount + "개를 구매했습니다.");
    }

    public void printLottoTickets(Lottos lottos) {
        for (Lotto lotto : lottos.getLottoList()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printWinningStatistics(Map<LottoRank, Integer> lottoResult, Double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (" + LottoRank.FIFTH.getPrize() + "원)- " + lottoResult.getOrDefault(LottoRank.FIFTH, 0) + "개");
        System.out.println("4개 일치 (" + LottoRank.FOURTH.getPrize() + "원)- " + lottoResult.getOrDefault(LottoRank.FOURTH, 0) + "개");
        System.out.println("5개 일치 (" + LottoRank.THIRD.getPrize() + "원)- " + lottoResult.getOrDefault(LottoRank.THIRD, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(" + LottoRank.SECOND.getPrize() + "원) - " + lottoResult.getOrDefault(LottoRank.SECOND, 0) + "개");
        System.out.println("6개 일치 (" + LottoRank.FIRST.getPrize() + "원)- " + lottoResult.getOrDefault(LottoRank.FIRST, 0) + "개");
        if (profitRate > 1) {
            System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 이익이라는 의미임)%n", profitRate);
            return;
        }
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)%n", profitRate);
    }
}
