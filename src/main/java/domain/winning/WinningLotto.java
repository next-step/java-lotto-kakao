package domain.winning;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;

import java.util.List;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusLottoNumber;

    public WinningLotto(List<Integer> input, int bonusNumber) {
        List<LottoNumber> lottoNumbers = input.stream()
                .map(LottoNumber::new)
                .toList();
        this.winningLotto = new Lotto(lottoNumbers);
        this.bonusLottoNumber = new LottoNumber(bonusNumber);
        validateNumber();
    }

    private void validateNumber() {
        if (winningLotto.contains(bonusLottoNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public WinningStatus compare(Lotto lotto) {
        // 보너스 번호 포함 여부 확인
        boolean containsBonus = lotto.contains(bonusLottoNumber);
        // 일치하는 번호 개수 세기
        int matchCount = winningLotto.countMatch(lotto);
        
        return WinningStatus.valueOf(matchCount, containsBonus);
    }
}
