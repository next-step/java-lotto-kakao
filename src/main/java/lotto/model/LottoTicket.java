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
        if(!validate(lottoNumbers)){
            throw new RuntimeException("로또 티켓에는 "+TICKET_SIZE+"개의 번호가 필요합니다.");
        }
        this.lottoNumbers = lottoNumbers;
    }

    private static Set<LottoNumber> toLottoNumberSet(Integer... numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("번호는 null일 수 없습니다.");
        }

        return Arrays.stream(numbers)
                .map(LottoNumber::new) // LottoNumber에 int를 받는 생성자가 있다고 가정
                .collect(Collectors.toCollection(TreeSet::new));
    }

    static boolean validate(Set<LottoNumber> lottoNumbers){
        return lottoNumbers.size() == TICKET_SIZE;
    }

    public boolean duplicateNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int duplicateNumber(LottoTicket ticket2) {
        return (int)lottoNumbers.stream().filter(num -> ticket2.duplicateNumber(num)).count();
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
