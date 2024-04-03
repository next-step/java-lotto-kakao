package lotto.domain;

import java.util.List;

public class WinningNumbers {

    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        this(new LottoTicket(winningNumbers), bonusNumber);
    }

    public WinningNumbers(LottoTicket winningTicket, LottoNumber bonusNumber) {
        validateWinningNumber(winningTicket, bonusNumber);
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    public Prize checkWinning(LottoTicket lottoTicket) {
        int matchCount = winningTicket.compare(lottoTicket);
        if (matchCount <= 2) {
            return Prize.NOTHING;
        }
        return getPrize(lottoTicket, matchCount);
    }

    private Prize getPrize(LottoTicket lottoTicket, int matchCount) {
        if (matchCount == 3) {
            return Prize.FIFTH;
        }
        if (matchCount == 4) {
            return Prize.FOURTH;
        }
        if (matchCount == 5 && !lottoTicket.contains(bonusNumber)) {
            return Prize.THIRD;
        }
        if (matchCount == 5 && lottoTicket.contains(bonusNumber)) {
            return Prize.SECOND;
        }
        return Prize.FIRST;
    }

    private void validateWinningNumber(LottoTicket winningTicket, LottoNumber bonusLottoNumber) {
        if (winningTicket.contains(bonusLottoNumber)) {
            throw new RuntimeException("보너스볼은 당첨 번호와 중복되지 않아야 합니다.");
        }
    }
}
