package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicketSeller {

    public List<LottoTicket> generateTickets(LottoPurchaseBudget budget) {
        int ticketQuantity = budget.getTicketQuantity();

        List<LottoTicket> tickets = new ArrayList<>();
        List<LottoNumber> lottoNumbers = LottoNumber.getValues();
        for (int i = 0; i < ticketQuantity; i++) {
            Collections.shuffle(lottoNumbers);
            tickets.add(new LottoTicket(lottoNumbers.subList(0, 6)));
        }

        return tickets;
    }
}
