package lotto.domain;

public class LottoResult {
    private final int ballCount;
    private final boolean isCorrectBonus;

    public LottoResult(int ballCount, boolean isCorrectBonus) {
        this.ballCount = ballCount;
        this.isCorrectBonus = isCorrectBonus;
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isCorrectBonus() {
        return isCorrectBonus;
    }

    public Rank calResult() {
        return Rank.valueOf(ballCount, isCorrectBonus);
    }
}
