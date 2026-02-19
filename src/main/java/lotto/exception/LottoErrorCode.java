package lotto.exception;

public enum LottoErrorCode {
    INVALID_NUMBER_RANGE("올바르지 않은 로또 번호입니다."),
    INVALID_LOTTO_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    INVALID_MATCH_COUNT("로또 당첨 개수가 올바르지 않습니다."),
    EXCEED_MANUAL_COUNT("수동 구매 장수가 전체 구입 장수보다 많습니다."),
    NEGATIVE_TOTAL_COUNT("총 구매 수량은 음수가 될 수 없습니다."),
    NEGATIVE_MANUAL_COUNT("수동 구매 장수는 음수가 될 수 없습니다.");

    private final String message;

    LottoErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
