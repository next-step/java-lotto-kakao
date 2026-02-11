package level1.domain;

import java.util.List;

public class AnswerLottery extends Lottery {

    private final int bonusLotteryNumber;

    public AnswerLottery(List<String> lottery, String bonusLottery) {
        super(lottery);

        this.bonusLotteryNumber = super.parseNumber(bonusLottery);

        if (this.lottery.contains(bonusLotteryNumber)) {
            throw new RuntimeException("보너스 볼은 기존 로또 번호와 중복되지 않아야 합니다.");
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
