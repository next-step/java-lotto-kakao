package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerLottoTest {

    @Test
    @DisplayName("로또 번호 일치 개수 검증")
    void judgeLottoNum() {
        AnswerLotto targetLotto = new AnswerLotto(new HashSet<>(List.of(1, 2, 3, 4, 5, 10)), 7);
        LottoBalls userLotto = new LottoBalls(new HashSet<>(List.of(1, 2, 3, 4, 5, 6)));

        LottoResult lottoResult = targetLotto.judge(userLotto);

        assertThat(lottoResult.getBallCount()).isEqualTo(5);
        assertThat(lottoResult.isCorrectBonus()).isFalse();
    }
}
