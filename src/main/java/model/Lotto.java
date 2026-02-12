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

    public Rank getRank(GameScore gameScore) {
        int matchCount = 0;
        for (var winNumber : gameScore.getWinNumbers()) {
            matchCount += (numberMask & (1L << winNumber)) == 0 ? 0 : 1;
        }
        boolean bonus = (numberMask & (1L << gameScore.getBonusNumber())) > 0;
        return Rank.make(matchCount, bonus);
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
