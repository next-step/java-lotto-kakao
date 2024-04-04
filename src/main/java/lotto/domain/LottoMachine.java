package lotto.domain;

import lotto.domain.dto.LottoResultDto;
import lotto.domain.dto.TicketDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoMachine {

    private final List<LottoTicket> tickets = new ArrayList<>();

    public List<TicketDto> generateTickets(Budget budget) {
        int ticketQuantity = budget.getTicketQuantity();

        List<LottoNumber> lottoNumbers = LottoNumber.getValues();
        for (int i = 0; i < ticketQuantity; i++) {
            Collections.shuffle(lottoNumbers);
            tickets.add(new LottoTicket(lottoNumbers.subList(0, 6)));
        }

        return tickets.stream()
            .map(LottoTicket::toDto)
            .collect(Collectors.toList());
    }

    public LottoResultDto getResult(WinningNumbers winningNumbers) {
        Map<Prize, Integer> result = new HashMap<>();
        for (Prize prize : Prize.values()) {
            result.put(prize, 0);
        }

        for (LottoTicket ticket : tickets) {
            Prize prize = winningNumbers.checkWinning(ticket);
            result.compute(prize, (key, oldValue) -> oldValue == null ? 0 : oldValue + 1);
        }

        double resultRate = PrizeCalculator.calculate(result);

        return new LottoResultDto(result, resultRate);
    }
}
