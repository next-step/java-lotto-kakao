package model;

public class LottoNumber {

    private static final int MIN = 1;
    private static final int MAX = 45;

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validate(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }

    private void validate(int lottoNumber) {
        if (lottoNumber < MIN || lottoNumber > MAX) {
            throw new IllegalArgumentException(MIN + "에서 " + MAX + "사이의 숫자를 입력해 주세요.");
        }
    }
}
