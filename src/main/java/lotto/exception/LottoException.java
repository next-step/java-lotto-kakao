package lotto.exception;

public class LottoException extends RuntimeException {

    public LottoException(LottoErrorCode exceptionCode) {
        super(exceptionCode.getMessage());
    }
}
