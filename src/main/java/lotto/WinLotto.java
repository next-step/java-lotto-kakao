package lotto;

public class WinLotto {
    private final LottoNumber bonus;
    private final Lotto win;

    public WinLotto(int bonus, Integer... lottoNumbers) {
        this(new LottoNumber(bonus), (new Lotto(lottoNumbers)));
    }

    public WinLotto(LottoNumber bonus, Lotto win) {
        this.bonus = bonus;
        this.win = win;
    }

    public LottoRank lottery(Lotto buy) {
        int count = buy.matchCount(win);
        return LottoRank.searchRank(count, buy.hasBonus(bonus));
    }
}
