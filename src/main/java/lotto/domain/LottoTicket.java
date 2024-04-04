package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {

    private static final int LOTTO_NUMBERS_COUNT = 6;
    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(Set<LottoNumber> lottoNumbers) {
        validateLottoTicket(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        this(new HashSet<>(lottoNumbers));
    }

    public int compare (LottoTicket otherNumbers) {
        return (int) lottoNumbers.stream()
            .filter(otherNumbers::contains)
            .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> toList() {
        return List.copyOf(lottoNumbers);
    }

    private static void validateLottoTicket(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않는 " + LOTTO_NUMBERS_COUNT + "개의 수로 구성되어야 합니다.");
        }
    }
}
