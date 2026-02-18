package model;

import java.util.List;

public class LottoNumbers {

    private static final int SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    private void validate(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE) {
            throw new IllegalArgumentException("숫자는 " + SIZE + "개만 입력해야 합니다.");
        }
        long numberMask = 0L;
        for (LottoNumber lottoNumber : lottoNumbers) {
            if ((numberMask & (1L << lottoNumber.getLottoNumber())) != 0) {
                throw new IllegalArgumentException("중복된 숫자를 입력할 수 없습니다.");
            }
            numberMask |= 1L << lottoNumber.getLottoNumber();
        }
    }
}
