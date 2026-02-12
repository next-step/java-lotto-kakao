package model;

import static model.Constants.MIN_NUMBER;
import static model.Constants.MAX_NUMBER;

public class LottoNumber {

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validate(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    private void validate(int lottoNumber) {
        if (lottoNumber < MIN_NUMBER || lottoNumber > MAX_NUMBER) {
            throw new IllegalArgumentException(MIN_NUMBER + "에서 " + MAX_NUMBER + "사이의 숫자를 입력해 주세요.");
        }
    }
}
