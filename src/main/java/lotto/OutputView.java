package lotto;

import java.util.Arrays;
import java.util.Comparator;

public class OutputView {
    public static void printPurchaseCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottoBundle(LottoBundle bundle) {
        bundle.getLottos()
                .forEach(System.out::println);

    }

    public static void printStatisticsHeader() {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
    }

    public static void printResult(LottoResult lottoResult) {
        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .sorted(Comparator.comparingInt(Rank::getWinningMoney))
                .forEach(rank -> printRankLine(rank, lottoResult.getCount(rank)));
    }

    private static void printRankLine(Rank rank, int count) {
        System.out.println(RankMessage.format(rank) + " - " + count + "개");
    }

    public static void printYield(double yield) {
        System.out.printf("총 수익률은 %.2f입니다.\n", yield);
    }
}
