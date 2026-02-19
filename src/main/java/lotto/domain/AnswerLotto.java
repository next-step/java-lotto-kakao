package lotto.domain;

import java.util.Set;

public class AnswerLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNum;

    public AnswerLotto(Set<LottoNumber> lottoNums, int bonusNum) {

        this.lotto = new Lotto(lottoNums);
        this.bonusNum = new LottoNumber(bonusNum);
    }

    public LottoResult judge(Lotto other) {
        boolean isCorrectBonus = isBonusCorrect(other);

        int ballCount = countBall(other);

        return new LottoResult(ballCount, isCorrectBonus);
    }

    private int countBall(Lotto other) {
        int ballCount = 0;

        for (LottoNumber lottoNumber : this.lotto.getLotto()) {

            ballCount += matchBall(other, lottoNumber);
        }
        return ballCount;
    }

    private int matchBall(Lotto other, LottoNumber lottoNumber) {
        if (other.getLotto().contains(lottoNumber)) {

            return 1;
        }
        return 0;
    }

    private boolean isBonusCorrect(Lotto other) {
        return other.getLotto().contains(this.bonusNum);

    }
}
