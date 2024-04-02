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
        int matchCount = (int) numbers.stream().filter(lottoTicket.getLottoNumbers()::contains).count();
        boolean isBonusMatch = lottoTicket.getLottoNumbers().contains(bonusNumber);
        return LottoRank.of(matchCount, isBonusMatch);
    }
}
