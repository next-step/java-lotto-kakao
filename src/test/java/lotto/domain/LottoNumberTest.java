package lotto.domain;

import lotto.exception.ExceptionCode;
import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @Test
    @DisplayName("로또 번호 범위 검증 - 실패 (0인 경우)")
    void lottoNumRangeUnder() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(LottoException.class)
                .hasMessage(ExceptionCode.INVALID_NUMBER_RANGE.getMsg());
    }

    @Test
    @DisplayName("로또 번호 범위 검증 - 실패 (46인 경우)")
    void lottoNumRangeOver() {
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(LottoException.class)
                .hasMessage(ExceptionCode.INVALID_NUMBER_RANGE.getMsg());
    }

    @Test
    @DisplayName("로또 번호 범위 검증 - 성공")
    void lottoNumRangeSuccess() {

        assertThatCode(() -> new LottoNumber(1))
                .doesNotThrowAnyException();

        assertThatCode(() -> new LottoNumber(45))
                .doesNotThrowAnyException();
    }
}
