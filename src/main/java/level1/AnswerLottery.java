package level1;

import java.util.List;

public class AnswerLottery extends Lottery {

    private final LottoNumber bonusLotteryNumber;

    public AnswerLottery(List<String> lottery, String bonusLottery) {
        super(lottery);
        this.bonusLotteryNumber = new LottoNumber(bonusLottery);
        validateBonusNumber();
    }

    private void validateBonusNumber() {
        if (super.contains(bonusLotteryNumber)) {
            throw new IllegalArgumentException("보너스 볼은 기존 로또 번호와 중복되지 않아야 합니다.");
        }
    }

    public Match judge(Lottery givenLottery) {
        long matchCount = this.lottery.stream()
                .filter(givenLottery::contains)
                .count();

        boolean bonusBallMatch = givenLottery.contains(this.bonusLotteryNumber);

        return Match.matchOf(matchCount, bonusBallMatch);
    }
}
