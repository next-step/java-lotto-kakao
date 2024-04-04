package com.lotto.model;

import java.util.List;

public class TargetLotto {
    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public TargetLotto(List<Integer> numbers, int bonusNumber) {
        validateDuplication(numbers, bonusNumber);
        this.lottoNumbers = LottoNumbers.valueOf(numbers);
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
    }

    public LottoRank match(LottoNumbers lottoNumbers) {
        int matchCount = lottoNumbers.getMatchCount(this.lottoNumbers);
        boolean bonusMatched = lottoNumbers.contains(bonusNumber);
        return LottoRank.of(matchCount, bonusMatched);
    }

    private void validateDuplication(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 로또 번호와 같을 수 없습니다.");
        }
    }
}
