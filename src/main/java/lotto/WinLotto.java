package lotto;

import java.util.List;

public class WinLotto {
    private final LottoNumber bonus;
    private final Lotto win;

    public WinLotto(int bonus, List<Integer> lottoNumbers) {
        this(LottoNumber.of(bonus), (new Lotto(lottoNumbers)));
    }

    private WinLotto(LottoNumber bonus, Lotto win) {
        validate(bonus, win);
        this.bonus = bonus;
        this.win = win;
    }

    private void validate(LottoNumber bonus, Lotto win) {
        if (win.contains(bonus)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public LottoRank lottery(Lotto buy) {
        int count = buy.matchCount(win);
        return LottoRank.searchRank(count, buy.hasBonus(bonus));
    }
}
