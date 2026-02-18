package view;

import lotto.Lotto;
import lotto.LottoBundle;
import lotto.LottoBundleResult;
import lotto.LottoNumber;
import lotto.LottoRank;
import lotto.PurchasePlan;
import money.Money;

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

    public void printPurchasedLottos(PurchasePlan purchasePlan, LottoBundle lottoBundle) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n",
                purchasePlan.getManualCount(),
                purchasePlan.getAutoCount());
        for (Lotto lotto : lottoBundle.asList()) {
            System.out.println(formatLotto(lotto));
        }
    }

    private String formatLotto(Lotto lotto) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (LottoNumber number : lotto.numbers()) {
            joiner.add(String.valueOf(number.value()));
        }
        return joiner.toString();
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

        System.out.printf("%s (%s) - %d개%n", label, formatMoney(rank.getPrize()), count);
    }

    private String formatMoney(Money money) {
        return money.value() + "원";
    }

    private String labelOf(LottoRank rank) {
        if (rank.isSecond()) {
            return "5개 일치, 보너스 볼 일치";
        }
        return String.format("%d개 일치", rank.getMatchCount());
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
