package lotto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningNumbers {
    private final LottoNumbers numbers;
    private final LottoNumber bonus;

    public WinningNumbers(List<Integer> numbers, Integer bonusNumber) {
        // 보너스 번호 중복 검증
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 중복될 수 없습니다.");
        }
        this.numbers = new LottoNumbers(numbers);
        this.bonus = new LottoNumber(bonusNumber);
    }

    public int getBonusNumber() {
        return bonus.value();
    }

    public LottoResult compare(LottoNumbers other) {
        final List<Integer> otherNumbers = other.getNumbers();
        int matchCount = calculateIntersectionSize(otherNumbers);
        boolean isBonusMatched = otherNumbers.contains(bonus.value());
        return determineResult(matchCount, isBonusMatched);
    }

    private int calculateIntersectionSize(List<Integer> otherNumbers) {
        return (int) numbers.getNumbers()
            .stream()
            .filter(otherNumbers::contains)
            .count();
    }

    private LottoResult determineResult(int matchCount, boolean bonusMatched) {
        if (matchCount == 6) return LottoResult.RANK_FIRST;
        if (matchCount == 5 && bonusMatched) return LottoResult.RANK_SECOND;
        if (matchCount == 5) return LottoResult.RANK_THIRD;
        if (matchCount == 4) return LottoResult.RANK_FOURTH;
        if (matchCount == 3) return LottoResult.RANK_FIFTH;
        return LottoResult.RANK_NONE;
    }

    public Map<LottoResult, Integer> countByRank(List<LottoNumbers> purchasedNumbers) {
        Map<LottoResult, Integer> rank = new HashMap<>();
        for (LottoNumbers numbers : purchasedNumbers) {
            final LottoResult result = compare(numbers);
            rank.merge(result, 1, Integer::sum);
        }
        return rank;
    }
}
