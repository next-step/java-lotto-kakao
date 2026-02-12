package model;

public class LottoResult {

    private final LottoNumbers mainNumbers;
    private final LottoNumber bonusNumber;

    public LottoResult(LottoNumbers mainNumbers, LottoNumber bonusNumber) {
        validate(mainNumbers, bonusNumber);
        this.mainNumbers = mainNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoNumbers getMainNumbers() {
        return mainNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validate(LottoNumbers mainNumbers, LottoNumber bonusNumber) {
        for (LottoNumber lottoNumber : mainNumbers.getLottoNumbers()) {
            if (lottoNumber.equals(bonusNumber)) {
                throw new IllegalArgumentException("당첨 번호와 보너스 볼이 같을 수 없습니다.");
            }
        }
    }
}
