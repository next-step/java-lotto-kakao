package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static lotto.support.LottoTestFixture.lottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class LottoNumberListTest {

    @Test
    @DisplayName("로또 번호 생성 테스트")
    void lottoNumberCreateTest() {
        Set<LottoNumber> lottoNums = lottoNumbers(1, 2, 3, 4, 5, 6);

        assertThatCode(() -> new Lotto(lottoNums))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 번호 일치 개수 검증")
    void judgeLottoNum() {
        AnswerLotto targetLotto = new AnswerLotto(
                lottoNumbers(1, 2, 3, 4, 5, 10),
                7
        );
        Lotto userLotto = new Lotto(lottoNumbers(1, 2, 3, 4, 5, 6));

        LottoResult lottoResult = targetLotto.judge(userLotto);

        assertThat(lottoResult.getBallCount()).isEqualTo(5);
        assertThat(lottoResult.isCorrectBonus()).isFalse();
    }

    @Test
    @DisplayName("로또 숫자 가져오기")
    void getLottoNumList() {
        Lotto myLotto = new Lotto(lottoNumbers(1, 2, 3, 4, 5, 6));
        String targetNumString = "[1, 2, 3, 4, 5, 6]";

        String lottoString = myLotto.getLottoString();

        assertThat(lottoString).isEqualTo(targetNumString);
    }
}
