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

//        if (count == 6) {
//            return LottoRank.FIRST;
//        }
//        if (count == 5 && buy.hasBonus(bonus)) {
//            return LottoRank.SECOND;
//        }
//        if (count == 5) {
//            return LottoRank.THIRD;
//        }
//        if (count == 4) {
//            return LottoRank.FOURTH;
//        }
//        if (count == 3) {
//            return LottoRank.FIFTH;
//        }
//
//        return LottoRank.LOSE;
    }
}
