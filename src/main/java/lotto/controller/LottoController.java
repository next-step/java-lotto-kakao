package lotto.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lotto.domain.*;
import lotto.domain.dto.LottoResultDto;
import lotto.domain.dto.TicketDto;
import lotto.view.LottoView;

public class LottoController {
    private final LottoTicketSeller seller;
    private final LottoView view;

    public LottoController() {
        this.seller = new LottoTicketSeller();
        this.view = new LottoView();
    }

    public void start() {
        LottoPurchaseBudget budget = getBudget();
        List<LottoTicket> tickets = seller.generateTickets(budget);

        // TODO
        List<TicketDto> ticketDtos = tickets.stream()
                .map(LottoTicket::toDto)
                .collect(Collectors.toList());

        view.printTickets(ticketDtos);

        WinningLotto winningLotto = getWinningLotto();

        LottoResult lottoResult = new LottoResult(winningLotto, tickets);

        // TODO
        LottoResultDto resultDto = new LottoResultDto(lottoResult.getResult(), lottoResult.getProfitRate(budget));
        view.printLottoResult(resultDto);
    }

    private LottoPurchaseBudget getBudget() {
        try {
            return new LottoPurchaseBudget(view.getBudget());
        } catch (RuntimeException e) {
            view.printError(e);
            return getBudget();
        }
    }

    private WinningLotto getWinningLotto() {
        try {
            List<Integer> inputNumbers = view.getNumbers();
            int bonusNumber = view.getBonusNumber();

            List<LottoNumber> lottoNumbers = inputNumbers.stream()
                    .map(LottoNumber::valueOf)
                    .collect(Collectors.toList());

            return new WinningLotto(lottoNumbers, LottoNumber.valueOf(bonusNumber));
        } catch (RuntimeException e) {
            view.printError(e);
            return getWinningLotto();
        }
    }
}
