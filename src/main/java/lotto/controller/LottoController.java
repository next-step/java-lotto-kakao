package lotto.controller;

import java.util.*;
import java.util.stream.Collectors;

import lotto.domain.*;
import lotto.dto.LottoResultDto;
import lotto.dto.TicketDto;
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
        int manualLottoCount = getManualLottoCount(budget);

        List<LottoTicket> tickets = getManualLottoTickets(manualLottoCount, budget);
        tickets.addAll(seller.purchaseAutoLotto(budget));
        view.printTickets(mapToTicketDto(tickets));

        WinningLotto winningLotto = getWinningLotto();
        LottoResult lottoResult = new LottoResult(winningLotto, tickets);

        view.printLottoResult(mapToResultDto(lottoResult, budget));
    }

    private LottoPurchaseBudget getBudget() {
        try {
            return new LottoPurchaseBudget(view.getBudget());
        } catch (RuntimeException e) {
            view.printError(e);
            return getBudget();
        }
    }

    private int getManualLottoCount(LottoPurchaseBudget budget) {
        try {
            int manualLottoCount = view.getManualLottoCount();
            budget.validatePurchasable(manualLottoCount);
            return manualLottoCount;
        } catch (RuntimeException e) {
            view.printError(e);
            return getManualLottoCount(budget);
        }
    }

    private List<LottoTicket> getManualLottoTickets(int manualLottoCount, LottoPurchaseBudget budget) {
        try {
            List<List<Integer>> manualLottoNumbers = view.getManualLottoNumbers(manualLottoCount);
             return seller.purchaseManualLotto(manualLottoNumbers, budget);
        } catch (RuntimeException e) {
            view.printError(e);
            return getManualLottoTickets(manualLottoCount, budget);
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

    private List<TicketDto> mapToTicketDto(List<LottoTicket> tickets) {
        List<TicketDto> ticketDtos = new ArrayList<>();
        for (LottoTicket ticket : tickets) {
            List<Integer> numbers = ticket.toList().stream()
                    .map(LottoNumber::getValue)
                    .collect(Collectors.toList());

            ticketDtos.add(new TicketDto(numbers));
        }
        return ticketDtos;
    }

    private LottoResultDto mapToResultDto(LottoResult lottoResult, LottoPurchaseBudget budget) {
        Map<Prize, Long> result = lottoResult.getResult();

        Map<String, Long> totalResult = new LinkedHashMap<>();
        Arrays.stream(Prize.values())
                .filter(prize -> prize != Prize.NOTHING)
                .sorted((a, b) -> b.getOrder() - a.getOrder())
                .forEach(prize -> totalResult.put(prize.toString(), result.getOrDefault(prize, 0L)));

        return new LottoResultDto(totalResult, lottoResult.getProfitRate(budget));
    }
}
