package lotto.exception;

public class LottoException extends RuntimeException {

    public LottoException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMsg());
    }
}
