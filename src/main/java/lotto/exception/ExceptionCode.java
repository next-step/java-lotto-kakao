package lotto.exception;

public enum ExceptionCode {
    INVALID_NUMBER_RANGE("올바르지 않은 로또 번호입니다."),
    INVALID_LOTTO_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    INVALID_MATCH_COUNT("로또 당첨 개수가 올바르지 않습니다.");

    private final String msg;

    ExceptionCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
