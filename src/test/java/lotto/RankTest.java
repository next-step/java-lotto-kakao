package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {

    @DisplayName("일치 개수와 보너스 볼 여부에 따른 등수 확인")
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "3, false, FIFTH",
            "2, false, MISS",
            "0, false, MISS"
    })
    void 일치_개수에_따른_등수_반환_테스트(int countOfMatch, boolean matchBonus, Rank expectedRank) {
        Rank actualRank = Rank.valueOf(countOfMatch, matchBonus);
        Assertions.assertThat(actualRank).isEqualTo(expectedRank);
    }

    @Test
    void 당첨금_계산_테스트() {
        Rank rank = Rank.FIRST;
        Assertions.assertThat(rank.winningMoney(2).money())
                .isEqualTo(4_000_000_000L);
    }
}