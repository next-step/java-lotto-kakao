package view;

import model.Rank;

public class StatsBoardView {

    public void showStatResult() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void showWinCountMessage(Rank rank, int count) {
        System.out.printf("%s (%d원) - %d개\n", rank.getDescription(), rank.getPrice(), count);
    }

    public void showProfitMessage(double profitRatio) {
        System.out.printf("총 수익률은 %.2f입니다.\n", profitRatio);
    }
}
