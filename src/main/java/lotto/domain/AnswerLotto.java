package lotto.domain;

import java.util.Set;

public class AnswerLotto {
    private final LottoBalls lottoBalls;
    private final LottoNumber bonusNum;

    public AnswerLotto(Set<Integer> lottoNums, int bonusNum) {

        this.lottoBalls = new LottoBalls(lottoNums);
        this.bonusNum = new LottoNumber(bonusNum);
    }

    public LottoResult judge(LottoBalls other) {
        boolean isCorrectBonus = isBonusCorrect(other);

        int ballCount = countBall(other);

        return new LottoResult(ballCount, isCorrectBonus);
    }

    private int countBall(LottoBalls other) {
        int ballCount = 0;

        for (LottoNumber lottoNumber : this.lottoBalls.getLottoBalls()) {
            ballCount += matchBall(other, lottoNumber);
        }
        return ballCount;
    }

    private int matchBall(LottoBalls other, LottoNumber lottoNumber) {
        if (other.getLottoBalls().contains(lottoNumber)) {
            return 1;
        }
        return 0;
    }

    private boolean isBonusCorrect(LottoBalls other) {
        return other.getLottoBalls().contains(this.bonusNum);
    }
}
