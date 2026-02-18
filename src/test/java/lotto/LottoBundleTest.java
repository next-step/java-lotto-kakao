package lotto;

import money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoBundleTest {
    private WinLotto win;

    @BeforeEach
    void setUp() {
        win = new WinLotto(7, 1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("로또 번들에 null 값이 들어갈 수 없다.")
    void validateLottoNumberCount() {
        assertThrows(IllegalArgumentException.class,
                () -> new LottoBundle(null));
        assertThrows(IllegalArgumentException.class,
                () -> new LottoBundle(new Lotto(8, 21, 23, 41, 42, 43), (Lotto[]) null));
    }

    @Test
    @DisplayName("로또묶음과 당첨로또를 비교하여 최종 결과를 계산한다.")
    void evaluate() {
        LottoBundle lottoBundle = new LottoBundle(
                new Lotto(8, 21, 23, 41, 42, 43),
                new Lotto(3, 5, 11, 16, 32, 38),
                new Lotto(7, 11, 16, 35, 36, 44),
                new Lotto(1, 8, 11, 31, 41, 42),
                new Lotto(13, 14, 16, 38, 42, 45),
                new Lotto(7, 11, 30, 40, 42, 43),
                new Lotto(2, 13, 22, 32, 38, 45),
                new Lotto(23, 25, 33, 36, 39, 41),
                new Lotto(1, 3, 5, 14, 22, 45), // ✅ 여기만 1,3,5로 3개 일치
                new Lotto(5, 9, 38, 41, 43, 44),
                new Lotto(2, 8, 9, 18, 19, 21),
                new Lotto(13, 14, 18, 21, 23, 35),
                new Lotto(17, 21, 29, 37, 42, 45),
                new Lotto(3, 8, 27, 30, 35, 44)
        );

        LottoBundleResult lottoBundleResult = lottoBundle.evaluate(win);

        assertThat(lottoBundleResult.getRankCount(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(lottoBundleResult.getRankCount(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(lottoBundleResult.getRankCount(LottoRank.THIRD)).isEqualTo(0);
        assertThat(lottoBundleResult.getRankCount(LottoRank.SECOND)).isEqualTo(0);
        assertThat(lottoBundleResult.getRankCount(LottoRank.FIRST)).isEqualTo(0);
    }

    @Test
    void buyLottoBundle() {
        Money money = Money.won(14000);
        LottoBundle lottoBundle = LottoBundle.buy(money);
        assertThat(Lotto.calculatePurchasableCount(money)).isEqualTo(lottoBundle.size());
    }

    @Test
    void buyLottoBundleWithInjectedGenerator() {
        Money money = Money.won(3000);
        Lotto fixedLotto = new Lotto(1, 2, 3, 4, 5, 6);
        LottoGenerator fixedGenerator = new LottoGenerator() {
            @Override
            public Lotto generate() {
                return fixedLotto;
            }
        };

        LottoBundle lottoBundle = LottoBundle.buy(money, fixedGenerator);

        assertThat(lottoBundle.size()).isEqualTo(3);
        assertThat(lottoBundle.asList()).isEqualTo(List.of(fixedLotto, fixedLotto, fixedLotto));
    }

    @Test
    void buyLottoBundleByPurchasePlanWithInjectedGenerator() {
        Lotto manualLotto = new Lotto(8, 21, 23, 41, 42, 43);
        PurchasePlan purchasePlan = PurchasePlan.from(Money.won(3000), List.of(manualLotto));
        Lotto fixedLotto = new Lotto(1, 2, 3, 4, 5, 6);
        LottoGenerator fixedGenerator = new LottoGenerator() {
            @Override
            public Lotto generate() {
                return fixedLotto;
            }
        };

        LottoBundle lottoBundle = LottoBundle.buy(purchasePlan, fixedGenerator);

        assertThat(lottoBundle.size()).isEqualTo(3);
        assertThat(lottoBundle.asList()).isEqualTo(List.of(manualLotto, fixedLotto, fixedLotto));
    }

    @Test
    void buyFailLottoBundle() {
        Money money = Money.won(900);
        assertThrows(IllegalArgumentException.class,
                () -> LottoBundle.buy(money));
    }
}
