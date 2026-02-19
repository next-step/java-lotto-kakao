package lotto;

import java.util.*;

public class Lotto {
    // automatic
    private LottoNumbers lottoNumbers;

    public Lotto(List<Integer> numbers) {
        this.lottoNumbers = new LottoNumbers(numbers);
    }

    // 구매한 로또 리스트 반환
    public List<Integer> getLottoNumbersAsList() {
        // 로또 번호(Integer)만 담긴 리스트로 변환
        return lottoNumbers.getLottoNumberList().stream()
                .map(LottoNumber::getNumber)
                .toList();
    }

    public LottoRank evaluateRank(WinningLotto winningLotto) {
        int matchCount = 0;
        boolean bonusCount = winningLotto.isContainBonusNumber(lottoNumbers);

        for (LottoNumber lottoNumber : lottoNumbers.getLottoNumberList()) {
            matchCount += countMatch(lottoNumber, winningLotto);
        }

        return calculateLottoRank(matchCount, bonusCount);
    }

    public int countMatch(LottoNumber lottoNumber, WinningLotto winningLotto) {
        if(winningLotto.contains(lottoNumber)) return 1;
        return 0;
    }

    // 로또의 결과 Enum 반환
    public LottoRank calculateLottoRank(int matchCount, boolean bonusCount) {
        return LottoRank.valueOf(matchCount, bonusCount);
    }
}
