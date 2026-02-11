package level1.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AnswerLotteryTest {

    private static final List<String> answerLotteryNumbers = List.of("1", "2", "3", "4", "5", "6");
    private static final String bonusLotteryNumber = "10";

    private static Stream<Arguments> judgeLotteryArgs() {
        return Stream.of(
                Arguments.of(List.of("11", "12", "13", "14", "15", "16"), Match.NONE),
                Arguments.of(List.of("1", "12", "13", "14", "15", "16"), Match.NONE),
                Arguments.of(List.of("1", "2", "13", "14", "15", "16"), Match.NONE),
                Arguments.of(List.of("1", "2", "3", "14", "15", "16"), Match.THREE),
                Arguments.of(List.of("1", "2", "3", "4", "15", "16"), Match.FOUR),
                Arguments.of(List.of("1", "2", "3", "4", "5", "16"), Match.FIVE),
                Arguments.of(List.of("1", "2", "3", "4", "5", "10"), Match.FIVE_WITH_BONUS),
                Arguments.of(List.of("1", "2", "3", "4", "5", "6"), Match.SIX)
        );
    }

    @Test
    @DisplayName("보너스 볼은 기존 로또 번호와 중복되지 않는다.")
    void testDuplicateBonusLottery() {

        List<String> lottery = List.of(
                "1", "2", "3", "4", "5", "6"
        );
        String bonusLottery = "1";

        assertThatThrownBy(() -> new AnswerLottery(lottery, bonusLottery))
                .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @MethodSource("judgeLotteryArgs")
    @DisplayName("사용자 로또와 당첨 번호를 비교하여 일치하는 숫자를 반환한다")
    void testJudgeLottery(List<String> givenLotteryNumbers, Match expectedMatch) {

        AnswerLottery answerLottery = new AnswerLottery(answerLotteryNumbers, bonusLotteryNumber);
        Lottery givenLottery = new Lottery(givenLotteryNumbers);

        assertThat(answerLottery.judge(givenLottery)).isEqualTo(expectedMatch);
    }
}
