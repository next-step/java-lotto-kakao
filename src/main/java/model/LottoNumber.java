package model;

public class LottoNumber {

    private static final int MIN = 1;
    private static final int MAX = 45;

    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private void validate(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException(MIN + "에서 " + MAX + "사이의 숫자를 입력해 주세요.");
        }
    }
}
