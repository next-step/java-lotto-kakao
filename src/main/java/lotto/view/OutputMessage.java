package lotto.view;

public enum OutputMessage {
    INPUT_PURCHASE_AMOUNT("구입금액을 입력해 주세요.\n"),
    INPUT_MANUAL_LOTTO_COUNT("수동으로 구매할 로또 개수를 입력해 주세요.\n"),
    INPUT_MANUAL_LOTTO_NUMBERS("수동으로 구매할 번호를 입력해 주세요.\n"),
    PURCHASE_COUNT("수동으로 %d장, 자동으로 %d장을 구매했습니다.\n"),
    LOTTO_LIST("%s\n\n"),
    INPUT_WINNING_NUMBERS("지난 주 당첨 번호를 입력해주세요.\n"),
    INPUT_BONUS_NUMBER("보너스 볼을 입력해주세요.\n"),
    LOTTO_STATISTICS("당첨 통계\n---------\n"),
    LOTTO_PROFIT("총 수익률은 %.2f입니다.\n");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }
}
