package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class WinningRankTest {

    @ParameterizedTest(name = "matchCount={0}, bonusCount={1} -> {2}")
    @DisplayName("등수 판별")
    @CsvSource({
            "6, 0, FIRST",
            "5, 1, SECOND",
            "5, 0, THIRD",
            "4, 0, FOURTH",
            "4, 1, FOURTH",
            "3, 0, FIFTH",
            "2, 0, NONE"
    })
    public void getRank(int matchCount, int bonusCount, WinningRank expected) {
        WinningRank rank = WinningRank.getRank(matchCount, bonusCount);
        assertThat(rank).isEqualTo(expected);
    }

    @Test
    @DisplayName("결과 출력")
    void getInfoString() {
        WinningRank rank = WinningRank.THIRD;
        assertThat(rank.getInfoString()).isEqualTo("5개 일치 (1500000원)");
    }

    @Test
    @DisplayName("결과 출력(보너스 공 존재)")
    void getInfoStringWithBounce() {
        WinningRank rank = WinningRank.SECOND;
        assertThat(rank.getInfoString()).isEqualTo("5개 일치, 보너스 볼 일치 (30000000원)");
    }

    @Test
    @DisplayName("유효 등수 목록")
    void getValidRanks() {
        assertThat(WinningRank.getValidRanks())
                .doesNotContain(WinningRank.NONE)
                .startsWith(WinningRank.FIFTH)
                .endsWith(WinningRank.FIRST);
    }
}
