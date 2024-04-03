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

    private final List<TicketNumbers> tickets = new ArrayList<>();

    private final List<LottoNumber> lottoNumberPool;

    public LottoMachine(List<LottoNumber> lottoNumberPool) {
        this.lottoNumberPool = lottoNumberPool;
    }

    public List<TicketDto> generateTickets(Budget budget) {
        int ticketQuantity = budget.getTicketQuantity();

        for (int i = 0; i < ticketQuantity; i++) {
            Collections.shuffle(lottoNumberPool);
            tickets.add(new TicketNumbers(lottoNumberPool.subList(0, 6)));
        }

        return tickets.stream()
            .map(TicketNumbers::toDto)
            .collect(Collectors.toList());
    }

    public LottoResultDto getResult(WinningNumbers winningNumbers) {
        Map<Prize, Integer> result = new HashMap<>();
        for (Prize prize : Prize.values()) {
            result.put(prize, 0);
        }

        for (TicketNumbers ticket : tickets) {
            Prize prize = winningNumbers.checkWinning(ticket);
            result.compute(prize, (key, oldValue) -> oldValue == null ? 0 : oldValue + 1);
        }

        double resultRate = PrizeCalculator.calculate(result);

        return new LottoResultDto(result, resultRate);
    }
}
