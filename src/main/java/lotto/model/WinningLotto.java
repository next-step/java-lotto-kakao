package lotto.model;

public class WinningLotto {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningTicket, LottoNumber bonusNumber) {
        validate(winningTicket, bonusNumber);
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    private void validate(LottoTicket lottoTicket, LottoNumber bonusNumber) {
        if (lottoTicket.duplicateNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }


    public WinningRank checkRank(LottoTicket lottoTicket) {
        int matchCount = winningTicket.duplicateNumber(lottoTicket);
        int bounceCount = lottoTicket.duplicateNumber(bonusNumber) ? 1:0;
        return WinningRank.getRank(matchCount, bounceCount);
    }
}
