package lotto.domain;

import lotto.exception.LottoErrorCode;
import lotto.exception.LottoException;

import java.util.*;
import java.util.stream.Collectors;

public class LottoBalls {
    private final static int LENGTH = 6;
    private final static int PRICE = 1000;
    private final Set<LottoNumber> lotto;

    public LottoBalls(Set<Integer> lottoNumbers) {
        checkLottoLength(lottoNumbers);

        this.lotto = lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public LottoBalls(LottoBalls other) {
        this.lotto = new HashSet<>(other.lotto);
    }

    private static void checkLottoLength(Set<Integer> numSet) {
        if (numSet.size() != LENGTH) {
            throw new LottoException(LottoErrorCode.INVALID_LOTTO_NUMBER_COUNT);
        }
    }

    public static int getLength() {
        return LENGTH;
    }

    public static int getPrice() {
        return PRICE;
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
