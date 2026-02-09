package lotto;

import java.util.Set;

public class WinLotto {
    private final Lotto win;
    private final int bonus;

    public WinLotto(int bonus, Integer... lottoNumbers) {
        this((new Lotto(Set.of(lottoNumbers))), bonus);
    }

    public WinLotto(Lotto win, int bonus) {
        this.win = win;
        this.bonus = bonus;
    }

    public LottoRank lottery(Lotto buy) {
        int count = buy.matchCount(win);

        if (count == 6) {
            return LottoRank.FIRST;
        }
        if (count == 5 && buy.hasBonus(bonus)) {
            return LottoRank.SECOND;
        }
        if (count == 5) {
            return LottoRank.THIRD;
        }
        if (count == 4) {
            return LottoRank.FOURTH;
        }
        if (count == 3) {
            return LottoRank.FIFTH;
        }

        return LottoRank.LOSE;
    }
}
