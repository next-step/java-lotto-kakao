package model;

import java.util.Collection;
import java.util.Set;

public class LottoNumbers {

    private static final int SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public LottoNumbers(Collection<LottoNumber> lottoNumbers) {
        this.lottoNumbers = validate(lottoNumbers);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    private Set<LottoNumber> validate(Collection<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE) {
            throw new IllegalArgumentException("숫자는 " + SIZE + "개만 입력해야 합니다.");
        }
        Set<LottoNumber> uniqueNumbers = Set.copyOf(lottoNumbers);
        if (uniqueNumbers.size() != SIZE) {
            throw new IllegalArgumentException("중복된 숫자를 입력할 수 없습니다.");
        }
        return uniqueNumbers;
    }
}
