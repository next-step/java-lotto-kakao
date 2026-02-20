package lotto.domain;

import lotto.exception.LottoErrorCode;
import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @Test
    @DisplayName("로또 번호 범위 검증 - 실패 (0인 경우)")
    void lottoNumRangeUnder() {
        assertThatThrownBy(() -> LottoNumber.valueOf(0))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoErrorCode.INVALID_NUMBER_RANGE.getMessage());
    }

    @Test
    @DisplayName("로또 번호 범위 검증 - 실패 (46인 경우)")
    void lottoNumRangeOver() {
        assertThatThrownBy(() -> LottoNumber.valueOf(46))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoErrorCode.INVALID_NUMBER_RANGE.getMessage());
    }

    @Test
    @DisplayName("로또 번호 범위 검증 - 성공")
    void lottoNumRangeSuccess() {

        assertThatCode(() -> LottoNumber.valueOf(1))
                .doesNotThrowAnyException();

        assertThatCode(() -> LottoNumber.valueOf(45))
                .doesNotThrowAnyException();
    }
}
