package lotto.domain;

import lotto.exception.ExceptionCode;
import lotto.exception.LottoException;

import java.util.*;
import java.util.stream.Collectors;

public class LottoBalls {
    private final static int LOTTO_LENGTH = 6;
    private final Set<LottoNumber> lotto;

    public LottoBalls(Set<Integer> lottoNums) {
        checkLottoLength(lottoNums);

        this.lotto = lottoNums.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public LottoBalls(LottoBalls other) {
        this.lotto = new HashSet<>(other.lotto);
    }

    private static void checkLottoLength(Set<Integer> numSet) {
        if (numSet.size() != LOTTO_LENGTH) {
            throw new LottoException(ExceptionCode.INVALID_LOTTO_NUMBER_COUNT);
        }
    }

    public static int getLottoLength() {
        return LOTTO_LENGTH;
    }

    public Set<LottoNumber> getLottoBalls() {
        return lotto;
    }

    public String getLottoNumberString() {
        List<LottoNumber> lottoArr = new ArrayList<>(lotto);

        Collections.sort(lottoArr);
        return lottoArr.toString();
    }
}
