package model;

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
}
