package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LottoTest {

    @Test
    @DisplayName("숫자 6개를 포함한 한 장의 로또를 생성한다.")
    public void generateLottoTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertNotNull(lotto);
        assertThat(lotto.getLottoNumbersAsList()).hasSize(6);
    }

    @Test
    @DisplayName("매칭 수와 보너스 여부에 따라 당첨 등수를 반환한다.")
    public void calculateLottoRankTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.calculateLottoRank(6, false)).isEqualTo(LottoRank.FIRST);
        assertThat(lotto.calculateLottoRank(5, true)).isEqualTo(LottoRank.SECOND);
        assertThat(lotto.calculateLottoRank(5, false)).isEqualTo(LottoRank.THIRD);
        assertThat(lotto.calculateLottoRank(4, false)).isEqualTo(LottoRank.FOURTH);
        assertThat(lotto.calculateLottoRank(3, false)).isEqualTo(LottoRank.FIFTH);
        assertThat(lotto.calculateLottoRank(2, false)).isEqualTo(LottoRank.PENDING);
    }

}
