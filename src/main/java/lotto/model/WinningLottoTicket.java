package lotto.model;

import java.util.List;

public class WinningLottoTicket {

    private final LottoTicket lottoTicket;
    private final LottoNumber bonusNumber;

    public WinningLottoTicket(LottoTicket lottoTicket, LottoNumber bonusNumber) {
        validate(lottoTicket, bonusNumber);
        this.lottoTicket = lottoTicket;
        this.bonusNumber = bonusNumber;
    }

    public WinningLottoTicket(List<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        this(new LottoTicket(lottoNumbers), bonusNumber);
    }

    private static void validate(LottoTicket lottoTicket, LottoNumber bonusNumber) {
        if (lottoTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 기본 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public LottoRank match(LottoTicket matchingTicket) {
        int matchCount = (int) lottoTicket.getLottoNumbers().stream()
                .filter(matchingTicket::contains)
                .count();
        boolean bonusMatch = matchingTicket.contains(bonusNumber);
        return LottoRank.valueOf(matchCount, bonusMatch);
    }
}
