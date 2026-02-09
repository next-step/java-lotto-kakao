package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {
    private WinLotto win;

    @BeforeEach
    void setUp() {
        win = new WinLotto(7, 1, 2, 3, 4, 5, 6);
    }

    @Test
    void lose() {
        Lotto buy = new Lotto(7, 8, 9, 10, 11, 12);
        LottoRank rank = win.lottery(buy);
        assertThat(rank).isEqualTo(LottoRank.LOSE);
    }

    @Test
    void fifthWin() {
        Lotto buy = new Lotto(1, 2, 3, 10, 11, 12);
        LottoRank rank = win.lottery(buy);
        assertThat(rank).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    void fourthWin() {
        Lotto buy = new Lotto(1, 2, 3, 4, 11, 12);
        LottoRank rank = win.lottery(buy);
        assertThat(rank).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    void thirdWin() {
        Lotto buy = new Lotto(1, 2, 3, 4, 5, 12);
        LottoRank rank = win.lottery(buy);
        assertThat(rank).isEqualTo(LottoRank.THIRD);
    }

    @Test
    void secondWin() {
        Lotto buy = new Lotto(1, 2, 3, 4, 5, 7);
        LottoRank rank = win.lottery(buy);
        assertThat(rank).isEqualTo(LottoRank.SECOND);
    }

    @Test
    void firstWin() {
        Lotto buy = new Lotto(1, 2, 3, 4, 5, 6);
        LottoRank rank = win.lottery(buy);
        assertThat(rank).isEqualTo(LottoRank.FIRST);
    }
}
