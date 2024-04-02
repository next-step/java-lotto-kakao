package com.lotto.model;

import java.util.List;

public class TargetLotto {
    private final List<Integer> numbers;
    private final int bonusNumber;

    public TargetLotto(List<Integer> numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public LottoRank match(LottoTicket lottoTicket) {
        List<Integer> lottoNumbers = lottoTicket.getLottoNumbers();
        int matchCount = (int) numbers.stream()
                .filter(lottoNumbers::contains)
                .count();
        boolean bonusMatched = lottoNumbers.contains(bonusNumber);
        return LottoRank.of(matchCount, bonusMatched);
    }
}
