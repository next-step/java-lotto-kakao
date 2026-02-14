package lotto.model;

import java.util.List;

public class LottoTicketGenerator {
    LottoTicket generate(List<Integer> numbers){
        return new LottoTicket(
                numbers.stream().map(LottoNumber::of).toList()
        );
    }
}
