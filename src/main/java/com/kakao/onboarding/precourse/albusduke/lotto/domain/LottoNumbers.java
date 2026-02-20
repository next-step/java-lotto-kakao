package com.kakao.onboarding.precourse.albusduke.lotto.domain;

import java.util.*;

public class LottoNumbers {

    private static final int NUMBER_SIZE = 6;
    private static final String NUMBER_SIZE_ERR_MSG = "숫자 6개로 생성할 수 있습니다.";
    private static final String DUPLICATED_NUMBER_ERR_MSG = "중복된 숫자로 생성할 수 없습니다.";

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<Integer> numbers) {
        if (numbers.size() != NUMBER_SIZE) {
            throw new IllegalArgumentException(NUMBER_SIZE_ERR_MSG);
        }

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER_ERR_MSG);
        }

        lottoNumbers = new ArrayList<>();

        for (int number : numbers) {
            lottoNumbers.add(LottoNumber.of(number));
        }

    }

    public boolean hasNumber(LottoNumber number) {
        for (LottoNumber lottoNumber : lottoNumbers) {
            if (lottoNumber.equals(number)) {
                return true;
            }
        }

        return false;
    }

    public int countMatchingNumbers(LottoNumbers otherNumbers) {
        int count = 0;

        for (LottoNumber number : otherNumbers.lottoNumbers) {
            if (hasNumber(number)) {
                count++;
            }
        }

        return count;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lottoNumbers);
    }
}
