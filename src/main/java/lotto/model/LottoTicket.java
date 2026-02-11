package lotto.model;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {
    private final Set<LottoNumber> lottoNumbers;
    public static final int TICKET_SIZE = 6;

    public LottoTicket(Integer... numbers) {
        this(toLottoNumberSet(numbers));
    }

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        this(new TreeSet<>(lottoNumbers));
    }

    public LottoTicket(Set<LottoNumber> lottoNumbers){
        validateSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private static Set<LottoNumber> toLottoNumberSet(Integer... numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("번호는 null일 수 없습니다.");
        }

        return Arrays.stream(numbers)
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private static void validateSize(Set<LottoNumber> lottoNumbers){
        if (lottoNumbers.size() != TICKET_SIZE) {
            throw new IllegalArgumentException("로또 티켓에는 " + TICKET_SIZE + "개의 번호가 필요합니다.");
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int matchCount(LottoTicket other) {
        return (int)lottoNumbers.stream().filter(other::contains).count();
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
