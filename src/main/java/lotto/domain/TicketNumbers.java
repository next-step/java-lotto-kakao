package lotto.domain;

import lotto.domain.dto.TicketDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketNumbers extends Numbers {

    public TicketNumbers(Set<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
        if (lottoNumbers.size() != 6) {
            throw new RuntimeException("로또 번호는 중복되지 않는 6개의 수로 구성되어야 합니다.");
        }
    }

    public TicketNumbers(List<LottoNumber> lottoNumbers) {
        this(new HashSet<>(lottoNumbers));
    }

    public TicketDto toDto() {
        return new TicketDto(lottoNumbers.stream()
            .map(LottoNumber::toInteger)
            .collect(Collectors.toList()));
    }

}
