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

    public long getNumberMask() {
        return numberMask;
    }

    public WinLevel getWinLevel(GameScore gameScore) {
        int winMatchcount = 0;
        for (var winNumber : gameScore.getWinNumbers()) {
            winMatchcount += (numberMask & (1L << winNumber)) == 0 ? 0 : 1;
        }
        boolean bonusMatched = (numberMask & (1L << gameScore.getBonusNumber())) > 0;
        return WinLevel.make(winMatchcount, bonusMatched);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (LottoNumber number : lotto.getLottoNumbers()) {
            stringBuilder.append(number.getLottoNumber()).append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
