package view;

import lotto.*;

import java.util.List;
import java.util.StringJoiner;

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
            printLotto(lotto);
        }
    }

    private void printLotto(Lotto lotto) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (LottoNumber number : lotto.numbers()) {
            joiner.add("" + number.value());
        }

        System.out.println(joiner);
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

        System.out.printf("%s (%s원) - %d개%n", label, rank.getPrize().amount(), count);
    }

    private String labelOf(LottoRank rank) {
        if (rank.isSecond()) {
            return "5개 일치, 보너스 볼 일치";
        }
        return String.format("%d개 일치", rank.getMatchCount());
    }

    public void printProfitRate(double profitRate) {
        String resultMessage = getResultMessage(profitRate);
        System.out.printf("총 수익률은 %.2f입니다.(%s)%n", Math.floor(profitRate * 100) / 100, resultMessage);
    }

    private String getResultMessage(double profitRate) {
        final double EPS = 1e-9;

        if (profitRate > 1) {
            return "기준이 1이기 때문에 결과적으로 이익이라는 의미임";
        }
        if (Math.abs(profitRate - 1) <= EPS) {
            return "기준이 1이기 때문에 결과적으로 본전이라는 의미임";
        }
        return "기준이 1이기 때문에 결과적으로 손해라는 의미임";
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
