package lotto.exception;

public enum InputErrorCode {
    EMPTY_INPUT("입력값이 비어있습니다."),
    INVALID_NUMBER_FORMAT("올바르지 않은 숫자 형식입니다."),
    EXCEED_MANUAL_COUNT("수동 구매 장수가 전체 구입 장수보다 많습니다."),
    NEGATIVE_MANUAL_COUNT("수동 구매 장수는 음수가 될 수 없습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    InputErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + this.message;
    }
}
