package level1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import level1.domain.AnswerLottery;
import level1.domain.Lottery;
import level1.domain.Match;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReportTest {

    private static final AnswerLottery answerLottery = new AnswerLottery(
            List.of("1", "2", "3", "4", "5", "6"), "7"
    );

    @Test
    @DisplayName("당첨 번호와 비교하여 각 등수별 당첨 개수를 정확히 집계한다")
    void reportCountsMatchCorrectly() {

        List<Lottery> userLotteries = List.of(
                new Lottery(List.of("1", "2", "3", "4", "5", "6")),         // 1등
                new Lottery(List.of("1", "2", "3", "4", "5", "7")),         // 2등 (보너스 7 가정)
                new Lottery(List.of("10", "11", "12", "13", "14", "15"))    // 꽝
        );

        Report report = new Report(3000, answerLottery, userLotteries);

        assertThat(report.getMatchCount(Match.SIX)).isEqualTo(1);
        assertThat(report.getMatchCount(Match.FIVE_WITH_BONUS)).isEqualTo(1);
        assertThat(report.getMatchCount(Match.NONE)).isEqualTo(1);
    }

    @Test
    @DisplayName("당첨자가 없는 등수를 조회할 때 에러가 발생하지 않아야 한다")
    void getMatchCountReturnsZeroForMissingMatch() {

        List<Lottery> emptyLotteries = List.of(
                new Lottery(List.of("10", "11", "12", "13", "14", "15"))
        );

        Report report = new Report(1000, answerLottery, emptyLotteries);

        assertThatCode(() -> report.getMatchCount(Match.SIX))
                .doesNotThrowAnyException();
    }
}
