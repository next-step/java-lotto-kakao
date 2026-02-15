package lotto;

import java.util.Arrays;
import java.util.Comparator;

public class OutputView {
    public static void printPurchaseCount(int manualCount, int autoCount) {
        System.out.println("수동으로 "+ manualCount + "장, 자동으로 "+ autoCount+"개를 구매했습니다.");
    }

    public static void printLottoBundle(PurchasedLottoBundle bundle) {
        bundle.forEachLottoInOrder(System.out::println);
    }

    public static void printStatisticsHeader() {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
    }

    public static void printResult(LottoResult lottoResult) {
        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .sorted(Comparator.comparingInt(Rank::toWinningMoney))
                .forEach(rank -> printRankLine(rank, lottoResult.toCount(rank)));
    }

    private static void printRankLine(Rank rank, int count) {
        System.out.println(RankMessage.format(rank) + " - " + count + "개");
    }

    public static void printYield(double yield) {
        System.out.printf("총 수익률은 %.2f입니다.\n", yield);
    }
}
