package com.lotto.model;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateDuplication(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoNumbers valueOf(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        return new LottoNumbers(lottoNumbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int size() {
        return lottoNumbers.size();
    }

    public int getMatchCount(LottoNumbers lottoNumbers) {
        return (int) this.lottoNumbers.stream()
                .filter(lottoNumbers::contains)
                .count();
    }

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    private void validateDuplication(List<LottoNumber> lottoNumbers) {
        int distinctNumberSize = (int) lottoNumbers.stream().distinct().count();
        if (lottoNumbers.size() != distinctNumberSize) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
    }
}
