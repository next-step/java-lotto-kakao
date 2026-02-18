package lotto.domain;

import lotto.exception.ExceptionCode;
import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또가 완전하지 않으면(번호가 6개가 아니면) 예외 발생")
    void lottoNumberDuplicatedTest() {
        Set<Integer> lottoNums = new HashSet<>(List.of(1, 2, 3, 4, 4, 5));

        assertThatThrownBy(() -> new LottoBalls(lottoNums))
                .isInstanceOf(LottoException.class)
                .hasMessage(ExceptionCode.INVALID_LOTTO_NUMBER_COUNT.getMsg());
    }

    @Test
    @DisplayName("로또 번호가 정상 생성")
    void lottoNumberCreateTest() {
        Set<Integer> lottoNums = new HashSet<>(List.of(1, 2, 3, 4, 5, 6));
        Set<Integer> duplicatedLottoNums = new HashSet<>(List.of(1, 2, 3, 4, 5, 5, 6));

        assertThatCode(() -> new LottoBalls(lottoNums))
                .doesNotThrowAnyException();

        assertThatCode(() -> new LottoBalls(duplicatedLottoNums))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 숫자 가져오기")
    void getLottoNumList(){
        Set<Integer> lotto = new HashSet<>(List.of(1, 2, 3, 4, 5, 6));
        LottoBalls myLotto = new LottoBalls(lotto);
        String targetNumString = "[1, 2, 3, 4, 5, 6]";
        String lottoNumString = myLotto.getLottoNumberString();

        assertThat(lottoNumString).isEqualTo(targetNumString);
    }
}
