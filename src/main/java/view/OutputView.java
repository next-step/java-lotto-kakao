package view;

import lotto.Lotto;
import lotto.LottoBundle;
import lotto.LottoBundleResult;
import lotto.LottoRank;

import java.util.List;

public class OutputView {
    private static final List<LottoRank> PRINT_ORDER = List.of(
            LottoRank.FIFTH,
            LottoRank.FOURTH,
            LottoRank.THIRD,
            LottoRank.SECOND,
            LottoRank.FIRST
    );

    public void printPurchasedLottos(LottoBundle lottoBundle) {
        System.out.printf("%d개를 구매했습니다.%n", lottoBundle.size());
        for (Lotto lotto : lottoBundle.asList()) {
            System.out.println(lotto);
        }
    }

    public void printStatistic(LottoBundleResult lottoBundleResult) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (LottoRank rank : PRINT_ORDER) {
            printRankLine(rank, lottoBundleResult.getRankCount(rank));
        }
    }

    private void printRankLine(LottoRank rank, int count) {
        String label = labelOf(rank);

        System.out.printf("%s (%s) - %d개%n", label, rank.getPrize(), count);
    }

    private String labelOf(LottoRank rank) {
        if (rank == LottoRank.SECOND) {
            return "5개 일치, 보너스 볼 일치";
        }
        return String.format("%d개 일치", rank.matchCount);
    }

    public void printProfitRate(double profitRate) {
        final double EPS = 1e-9;

        String resultMessage;
        if (profitRate > 1) {
            resultMessage = "기준이 1이기 때문에 결과적으로 이익이라는 의미임";
        } else if (Math.abs(profitRate - 1) <= EPS) {
            resultMessage = "기준이 1이기 때문에 본전이라는 의미임";
        } else {
            resultMessage = "기준이 1이기 때문에 결과적으로 손해라는 의미임";
        }

        System.out.printf("총 수익률은 %.2f입니다.(%s)%n", Math.floor(profitRate * 100) / 100, resultMessage);
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
