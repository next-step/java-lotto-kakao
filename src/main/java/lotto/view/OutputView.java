package lotto.view;

import lotto.model.LottoTickets;
import lotto.model.WinningInfo;
import lotto.model.WinningRank;

public class OutputView {

    public void printPurchaseCount(int manualCount, int autoCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualCount, autoCount);
    }

    public void printTickets(LottoTickets ticketList) {
        System.out.println(ticketList);
    }

    public void printStatistics(WinningInfo winningInfo) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        for (WinningRank rank : WinningRank.getValidRanks()) {
            printRank(rank, winningInfo.getRankCount(rank));
        }
    }

    private void printRank(WinningRank rank, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append(rank.getMatchCount()).append("개 일치");
        if (rank.getBounceCount() != 0) {
            sb.append(", 보너스 볼 일치");
        }
        sb.append(" (").append(rank.winningPrice.getValue()).append("원)- ");
        sb.append(count).append("개");
        System.out.println(sb);
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
