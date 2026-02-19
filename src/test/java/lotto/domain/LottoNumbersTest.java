package lotto.domain;

import lotto.exception.ExceptionCode;
import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static lotto.support.LottoTestFixture.lottoNumbers;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또가 완전하지 않으면(번호가 6개가 아니면) 예외가 발생한다")
    void lottoNumberNotCompleteTest() {
        Set<LottoNumber> lottoNums = lottoNumbers(1, 2, 3, 4, 5);

        assertThatThrownBy(() -> new Lotto(lottoNums))
                .isInstanceOf(LottoException.class)
                .hasMessage(ExceptionCode.INVALID_LOTTO_NUMBER_COUNT.getMsg());
    }

    @Test
    @DisplayName("로또 번호가 정상적으로 생성된다")
    void lottoNumberCreateTest() {
        Set<LottoNumber> lottoNums = lottoNumbers(1, 2, 3, 4, 5, 6);

        assertThatCode(() -> new Lotto(lottoNums))
                .doesNotThrowAnyException();
    }
}
