package lotto.domain;

import java.util.Set;

public class Numbers {

    protected final Set<LottoNumber> lottoNumbers;

    public Numbers(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    protected int compare (Numbers otherNumbers) {
        return (int) lottoNumbers.stream()
            .filter(otherNumbers::contains)
            .count();
    }

    protected boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
