package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {
    @Test
    void lose() {
        Lotto win = new Lotto(1,2,3,4,5,6);
        Lotto buy = new Lotto(7,8,9,10,11,12);
        LottoRank rank = win.lottery(buy);
        assertThat(rank).isEqualTo(LottoRank.LOSE);
    }
}
