package lotto.exception;

public enum InputErrorCode {
    EMPTY_INPUT("입력값이 비어있습니다."),
    INVALID_NUMBER_FORMAT("올바르지 않은 숫자 형식입니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    InputErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + this.message;
    }
}
