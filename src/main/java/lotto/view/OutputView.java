package lotto.view;

import lotto.model.LottoTickets;
import lotto.model.WinningInfo;

public class OutputView {

    public void printPurchaseCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printPurchaseCount(int manualCount, int autoCount) {
        System.out.println("\n수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
    }

    public void printTickets(LottoTickets tickets) {
        System.out.println(tickets);
    }

    public void printStatistics(WinningInfo winningInfo) {
        System.out.println(winningInfo.statisticsString());
    }

    public void printRateOfReturn(double rate) {
        System.out.printf("총 수익률은 %.2f입니다.", rate);
        if (rate < 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        } else {
            System.out.println();
        }
    }

}
