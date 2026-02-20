package lotto.domain;

import lotto.exception.LottoErrorCode;
import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class LottoNumberListTest {

    @Test
    @DisplayName("로또 번호 중복 테스트")
    void lottoNumberDuplicatedTest() {
        Set<Integer> lottoNums = new LinkedHashSet<>(List.of(1, 2, 3, 4, 4, 5));

        assertThatThrownBy(() -> new LottoBalls(lottoNums))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoErrorCode.INVALID_LOTTO_NUMBER_COUNT.getMessage());
    }

    @Test
    @DisplayName("로또 번호 생성 테스트")
    void lottoNumberCreateTest() {
        Set<Integer> lottoNums = new LinkedHashSet<>(List.of(1, 2, 3, 4, 5, 6));

        assertThatCode(() -> new LottoBalls(lottoNums))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 번호 일치 개수 검증")
    void judgeLottoNum() {
        AnswerLotto targetLotto = new AnswerLotto(new HashSet<>(List.of(1, 2, 3, 4, 5, 10)), 7);
        LottoBalls userLotto = new LottoBalls(new HashSet<>(List.of(1, 2, 3, 4, 5, 6)));

        LottoResult lottoResult = targetLotto.judge(userLotto);

        assertThat(lottoResult.getBallCount()).isEqualTo(5);
        assertThat(lottoResult.isCorrectBonus()).isFalse();
    }

    @Test
    @DisplayName("로또 숫자 가져오기")
    void getLottoNumList(){
        Set<Integer> lotto = new LinkedHashSet<>(List.of(1, 2, 3, 4, 5, 6));
        LottoBalls myLotto = new LottoBalls(lotto);
        String targetNumString = "[1, 2, 3, 4, 5, 6]";
        String lottoNumString = myLotto.getLottoNumberString();

        assertThat(lottoNumString).isEqualTo(targetNumString);
    }
}
