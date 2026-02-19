package model;

public class Lotto {

    private final LottoNumbers lotto;
    private final long numberMask;

    public Lotto(LottoNumbers lotto) {
        long numberMask = 0L;
        for (LottoNumber lottoNumber : lotto.getLottoNumbers()) {
            numberMask |= (1L << lottoNumber.getLottoNumber());
        }
        this.lotto = lotto;
        this.numberMask = numberMask;
    }

    public LottoNumbers getLotto() {
        return lotto;
    }

    public long getNumberMask() {
        return numberMask;
    }

    public Rank getRank(LottoResult lottoResult) {
        int matchCount = 0;
        for (LottoNumber lottoNumber : lottoResult.getMainNumbers().getLottoNumbers()) {
            matchCount += (numberMask & (1L << lottoNumber.getLottoNumber())) == 0 ? 0 : 1;
        }
        boolean bonus = (numberMask & (1L << lottoResult.getBonusNumber().getLottoNumber())) != 0;
        return Rank.make(matchCount, bonus);
    }
}
