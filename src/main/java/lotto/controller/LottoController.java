package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.*;
import lotto.domain.dto.LottoResultDto;
import lotto.view.LottoView;

public class LottoController {
    private final LottoTicketSeller seller;
    private final LottoView view;

    public LottoController() {
        this.seller = new LottoTicketSeller();
        this.view = new LottoView();
    }

    public void start() {
        List<LottoTicket> tickets = createTickets();
        view.printTickets(tickets.stream().map(LottoTicket::toDto).collect(Collectors.toList()));

        LottoResultDto result = getResult(tickets);
        view.printLottoResult(result);
    }

    private List<LottoTicket> createTickets() {
        try {
            Budget budget = new Budget(view.getBudget());
            return seller.generateTickets(budget);
        } catch (RuntimeException e) {
            view.printError(e);
            return createTickets();
        }
    }

    private LottoResultDto getResult(List<LottoTicket> tickets) {
        try {
            List<Integer> inputNumbers = view.getNumbers();

            List<LottoNumber> lottoNumbers = inputNumbers.stream()
                    .map(LottoNumber::valueOf)
                    .collect(Collectors.toList());
            LottoNumber bonusLottoNumber = LottoNumber.valueOf(view.getBonusNumber());

            WinningLotto winningLotto = new WinningLotto(lottoNumbers, bonusLottoNumber);
            return winningLotto.getResult(tickets);
        } catch (RuntimeException e) {
            view.printError(e);
            return getResult(tickets);
        }
    }
}
