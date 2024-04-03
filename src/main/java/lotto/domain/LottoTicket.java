package lotto.domain;

import lotto.domain.dto.TicketDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {

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

    public TicketDto toDto() {
        return new TicketDto(lottoNumbers.stream()
                .map(LottoNumber::toInteger)
                .collect(Collectors.toList()));
    }

    private static void validateLottoTicket(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new RuntimeException("로또 번호는 중복되지 않는 6개의 수로 구성되어야 합니다.");
        }
    }
}
