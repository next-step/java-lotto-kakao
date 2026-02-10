package lotto.domain;

public class LottoNumber {

    public static final String RANGE_FAIL_MSG = "로또 번호의 범위는 1 ~ 45사이어야 합니다.";

    private final int value;

    public LottoNumber(int value) {
        validate(value);
        this.value = value;
    }

    public void validate(int value) {
        if (value < 1 || value > 45) {
            throw new IllegalArgumentException(RANGE_FAIL_MSG);
        }
    }

    public int getValue() {
        return value;
    }
}
