package lotto.domain;

import lotto.exception.ExceptionCode;
import lotto.exception.LottoException;

import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final int PRICE = 1000;
    private final static int LOTTO_LENGTH = 6;

    private final Set<LottoNumber> lotto;

    public Lotto(Set<LottoNumber> lotto) {
        checkLottoLength(lotto);
        this.lotto = lotto;
    }

    public Set<LottoNumber> getLotto() {
        return lotto;
    }

    public String getLottoString() {
        return lotto.stream()
                .sorted()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static int getPrice() {
        return PRICE;
    }

    private static void checkLottoLength(Set<LottoNumber> numSet) {
        if (numSet.size() != LOTTO_LENGTH) {
            throw new LottoException(ExceptionCode.INVALID_LOTTO_NUMBER_COUNT);
        }
    }

}
