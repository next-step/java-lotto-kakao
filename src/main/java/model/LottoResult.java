package model;

import java.util.Objects;

public class LottoResult {

    private final LottoNumbers mainNumbers;
    private final LottoNumber bonusNumber;

    public LottoResult(LottoNumbers mainNumbers, LottoNumber bonusNumber) {
        this.mainNumbers = mainNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoNumbers getMainNumbers() {
        return mainNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoResult lottoResult = (LottoResult) o;
        return Objects.equals(bonusNumber, lottoResult.bonusNumber) &&
                Objects.equals(mainNumbers, lottoResult.mainNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusNumber, mainNumbers);
    }
}
