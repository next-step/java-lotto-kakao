package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketSeller {

    public List<LottoTicket> purchaseAutoLotto(LottoPurchaseBudget budget) {
        int ticketQuantity = budget.getTicketQuantity();

        List<LottoTicket> tickets = new ArrayList<>();
        List<LottoNumber> lottoNumbers = LottoNumber.getValues();
        for (int i = 0; i < ticketQuantity; i++) {
            Collections.shuffle(lottoNumbers);
            tickets.add(new LottoTicket(lottoNumbers.subList(0, 6)));
        }

        return tickets;
    }

    public List<LottoTicket> purchaseManualLotto(List<List<Integer>> manualLottoNumbers, LottoPurchaseBudget budget) {
        List<LottoTicket> manualTickets = manualLottoNumbers.stream()
                .map(LottoTicket::of)
                .collect(Collectors.toList());

        budget.purchaseLotto(manualTickets.size());
        return manualTickets;
    }
}
