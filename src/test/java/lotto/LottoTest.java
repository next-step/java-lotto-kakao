package lotto;

import money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
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
        assertThrows(IllegalArgumentException.class,
                () -> new Lotto(1, 2));
    }

    @Test
    @DisplayName("랜덤 로또는 중복없이 6개의 유효한 숫자가 있어야 한다.")
    void createRandomLotto() {
        for (int i = 0; i < 10; i++) {
            Lotto lotto = Lotto.random();
            List<LottoNumber> numbers = lotto.numbers();

            System.out.println(numbers);

            assertThat(numbers).hasSize(6);
            assertThat(numbers)
                    .allSatisfy(n -> assertThat(n.value()).isBetween(1, 45));
        }
    }

    @Test
    void failsForInvalidMoney() {
        assertThrows(IllegalArgumentException.class,
                () -> Lotto.calculatePurchasableCount(Money.won(1400)));
    }

    @Test
    void failsForZeroMoney() {
        assertThrows(IllegalArgumentException.class,
                () -> Lotto.calculatePurchasableCount(Money.won(0)));
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
