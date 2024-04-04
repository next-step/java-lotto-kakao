package lotto.domain;

import lotto.domain.dto.LottoResultDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {

    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        this(new LottoTicket(winningNumbers), bonusNumber);
    }

    public WinningLotto(LottoTicket winningTicket, LottoNumber bonusNumber) {
        validateWinningNumber(winningTicket, bonusNumber);
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    public LottoResultDto getResult(List<LottoTicket> tickets) {
        Map<Prize, Integer> result = new HashMap<>();
        for (Prize prize : Prize.values()) {
            result.put(prize, 0);
        }

        for (LottoTicket ticket : tickets) {
            int matchCount = winningTicket.compare(ticket);
            boolean isBonusNumberMatched = ticket.contains(bonusNumber);
            Prize prize = Prize.evaluate(matchCount, isBonusNumberMatched);

            result.compute(prize, (key, oldValue) -> oldValue == null ? 0 : oldValue + 1);
        }

        double resultRate = PrizeCalculator.calculate(result);

        return new LottoResultDto(result, resultRate);
    }

    private void validateWinningNumber(LottoTicket winningTicket, LottoNumber bonusLottoNumber) {
        if (winningTicket.contains(bonusLottoNumber)) {
            throw new RuntimeException("보너스볼은 당첨 번호와 중복되지 않아야 합니다.");
        }
    }
}
