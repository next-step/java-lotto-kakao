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
        if (lottoTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public WinningRank rank(LottoTicket lottoTicket) {
        int matchCount = winningTicket.matchCount(lottoTicket);
        int bonusCount = lottoTicket.contains(bonusNumber) ? 1 : 0;
        return WinningRank.rank(matchCount, bonusCount);
    }

}
