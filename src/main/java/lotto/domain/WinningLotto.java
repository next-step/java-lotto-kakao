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

    public Prize calculatePrize(LottoTicket ticket) {
        int matchCount = winningTicket.compare(ticket);
        boolean isBonusNumberMatched = ticket.contains(bonusNumber);
        return Prize.evaluate(matchCount, isBonusNumberMatched);
    }

    private void validateWinningNumber(LottoTicket winningTicket, LottoNumber bonusLottoNumber) {
        if (winningTicket.contains(bonusLottoNumber)) {
            throw new RuntimeException("보너스볼은 당첨 번호와 중복되지 않아야 합니다.");
        }
    }
}
