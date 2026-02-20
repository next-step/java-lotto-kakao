package lotto.domain;

import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoResultTest {

    @DisplayName("입력에 맞는 등수 반환")
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, true, FOURTH",
            "3, false, FIFTH",
            "2, true, MISS",
            "1, false, MISS",
            "0, false, MISS"
    })
    void calResultTest(int ballCount, boolean isCorrectBonus, Rank expectedRank) {
        LottoResult lottoResult = new LottoResult(ballCount, isCorrectBonus);
        assertThat(lottoResult.calResult()).isEqualTo(expectedRank);
    }

    @Test
    @DisplayName("비정상적인 개수 들어오면 Rank 변환 시 예외 발생")
    void invalidBallCountTest() {
        LottoResult lottoResult = new LottoResult(7, false);

        assertThatThrownBy(lottoResult::calResult)
                .isInstanceOf(LottoException.class);
    }
}
