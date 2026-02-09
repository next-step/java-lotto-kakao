package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    private WinLotto win;

    @BeforeEach
    void setUp() {
        win = new WinLotto(7, 1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("로또 번호는 반드시 6개여야 한다")
    void validateLottoNumberCount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Lotto(1, 2, 3, 4, 5, 6, 7));
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
