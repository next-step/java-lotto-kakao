package domain.winning;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusLottoNumber;

    public WinningLotto(List<Integer> input, int bonusNumber) {
        this.bonusLottoNumber = LottoNumber.of(bonusNumber);
        this.winningLotto = new Lotto(input);
        validateNumber();
    }

    private void validateNumber() {
        Set<LottoNumber> lottoNumberSet = new HashSet<>(winningLotto.getNumbers());
        lottoNumberSet.add(bonusLottoNumber);
        if (lottoNumberSet.size() != 7) {
            throw new IllegalArgumentException("당첨 번호에 중복이 있습니다.");
        }
    }

    public WinningStatus compare(Lotto lotto) {
        int count = 0;
        // 보너스 번호 포함 여부 확인
        boolean containsBonus = lotto.getNumbers().contains(bonusLottoNumber);
        // 일치하는 번호 개수 세기
        Set<LottoNumber> winningNumbersSet = new HashSet<>(winningLotto.getNumbers());
        winningNumbersSet.retainAll(lotto.getNumbers());

        return WinningStatus.valueOf(winningNumbersSet.size(), containsBonus);
    }
}
