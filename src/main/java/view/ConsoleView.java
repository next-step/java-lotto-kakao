package view;

import controller.LottoGame;
import domain.Lotto;
import domain.Lottos;
import enumeration.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class ConsoleView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int getCash() {
        printCashPrompt();
        return SCANNER.nextInt();
    }

    private static void printCashPrompt() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printBoughtLottosPrompt(Lottos lottos) {
        printBunchSizePrompt(lottos.bunch().size());
        printLottosNumbersPrompt(lottos);
    }

    private static void printBunchSizePrompt(int bunchSize) {
        System.out.println(bunchSize + "개 구매했습니다.");
    }

    private static void printLottosNumbersPrompt(Lottos lottos) {
        StringBuilder sb = new StringBuilder();
        lottos.bunch().stream().map(ConsoleView::lottoToString).forEach(e -> sb.append(e).append("\n"));
        System.out.println(sb);
    }

    private static String lottoToString(Lotto lotto) {
        return lotto.numbers().stream()
                .map(e -> String.valueOf(e.value()))
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static List<Integer> getWinningNumbers() {
        printWinningNumbersPrompt();
        SCANNER.nextLine();
        String next = SCANNER.nextLine();
        return Arrays.stream(next.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void printWinningNumbersPrompt() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static int getWinningBonus() {
        printWinningBonusPrompt();
        return SCANNER.nextInt();
    }

    private static void printWinningBonusPrompt() {
        System.out.println("보너스 볼을 입력 주세요.");
    }

    public static void printStatistics(LottoGame game) {
        printWinningPrompt();
        List<Rank> ranks = game.ranks();
        System.out.println("3개 일치 (5000원)- " + filterRanksCount(ranks, 5) + "개");
        System.out.println("4개 일치 (50000원)- " + filterRanksCount(ranks, 4) + "개");
        System.out.println("5개 일치 (1500000원)- " + filterRanksCount(ranks, 3) + "개");
        System.out.println("5개 일치, 보너 볼 일치 (30000000원)- " + filterRanksCount(ranks, 2) + "개");
        System.out.println("6개 일치 (2000000000원)- " + filterRanksCount(ranks, 1) + "개");
        printProfitRate(getPrize(game.ranks()), game.cash());
    }

    private static void printWinningPrompt() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static int filterRanksCount(List<Rank> ranks, int rank) {
        return ranks.stream().filter(e -> e.rank() == rank).mapToInt(e -> 1).sum();
    }

    private static double getPrize(List<Rank> ranks) {
        return ranks.stream()
                .mapToLong(Rank::prize)
                .sum();
    }

    private static void printProfitRate(double prize, double cash) {
        System.out.println("총 수익률은 " + prize / cash + "입니다.");
    }
}
