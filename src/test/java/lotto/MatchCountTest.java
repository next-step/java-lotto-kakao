package lotto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.model.MatchCount;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchCountTest {

    @ParameterizedTest @CsvSource({
        "6, false, SIX",
        "6, true, SIX",
        "5, false, FIVE",
        "5, true, FIVE_BONUS",
        "4, false, FOUR",
        "4, true, FOUR",
        "3, false, THREE",
        "3, true, THREE",
        "2, false, NOTHING",
        "2, true, NOTHING",
        "1, false, NOTHING",
        "1, true, NOTHING"
    })
    void from_rankDecision_test(int matchCount, boolean hasBonus, MatchCount expected) {
        MatchCount actual = MatchCount.from(matchCount, hasBonus);
        assertThat(actual).isEqualTo(expected);
    }
}
