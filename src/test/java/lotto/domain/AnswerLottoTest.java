package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.support.LottoTestFixture.lottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswerLottoTest {

    @Test
    @DisplayName("로또 번호 일치 개수 검증")
    void judgeLottoNum() {
        AnswerLotto targetLotto = new AnswerLotto(
                lottoNumbers(1, 2, 3, 4, 5, 10),
                7
        );

        Lotto userLotto = new Lotto(
                lottoNumbers(1, 2, 3, 4, 5, 6)
        );

        LottoResult lottoResult = targetLotto.judge(userLotto);

        assertThat(lottoResult.getBallCount()).isEqualTo(5);
        assertThat(lottoResult.isCorrectBonus()).isFalse();
    }
}
