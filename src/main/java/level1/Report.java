package level1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import level1.domain.AnswerLottery;
import level1.domain.Lottery;
import level1.domain.Match;

public class Report {

    private final int price;

    private final MatchCountMap matchCountMap;

    public Report(int price, AnswerLottery answerLottery, List<Lottery> lotteries) {
        this.price = price;
        this.matchCountMap = new MatchCountMap(answerLottery, lotteries);
    }

    public int getMatchCount(Match match) {
        return matchCountMap.getMatchCount(match);
    }

    public String representReport() {
        StringBuilder sb = new StringBuilder("당첨 통계\n---------\n");

        appendMatchStatistics(sb);

        sb.append(String.format(
                "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)",
                calculateProfitRate()
        ));

        return sb.toString();
    }

    private void appendMatchStatistics(StringBuilder sb) {
        List<Match> winningMatches = List.of(
                Match.THREE, Match.FOUR, Match.FIVE, Match.FIVE_WITH_BONUS, Match.SIX
        );

        for (Match match : winningMatches) {
            sb.append(String.format(
                    "%s - %d개\n",
                    match.getDescription(), matchCountMap.getMatchCount(match)
            ));
        }
    }

    private double calculateProfitRate() {
        long totalPrize = Arrays.stream(Match.values())
                .mapToLong(m -> (long) m.getPrize() * matchCountMap.getMatchCount(m))
                .sum();

        return (double) totalPrize / price;
    }

    private static class MatchCountMap {

        private final Map<Match, Integer> map;

        private MatchCountMap(AnswerLottery answerLottery, List<Lottery> givenLotteries) {
            this.map = givenLotteries.stream().collect(
                    Collectors.groupingBy(
                            answerLottery::judge,
                            Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                    )
            );
        }

        private int getMatchCount(Match match) {
            return map.getOrDefault(match, 0);
        }
    }
}
