package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Budget;
import lotto.domain.LottoMachine;
import lotto.domain.dto.LottoResultDto;
import lotto.domain.Number;
import lotto.domain.dto.TicketDto;
import lotto.domain.WinningNumbers;
import lotto.view.LottoView;

public class LottoController {
    private final LottoMachine lottoMachine;
    private final LottoView view;
    private List<Number> numberPool;
    private boolean holdFlag;

    public LottoController() {
        initNumberPool();
        this.lottoMachine = new LottoMachine(numberPool);
        this.view = new LottoView();
        this.holdFlag = true;
    }

    public void start() {
        List<TicketDto> tickets = null;
        while (holdFlag) {
            tickets = createTickets();
        }
        view.printTickets(tickets);

        this.holdFlag = true;
        LottoResultDto result = null;
        while (holdFlag) {
            result = getResult();
        }
        view.printLottoResult(result);
    }

    private List<TicketDto> createTickets() {
        try {
            int budget = view.getBudget();
            List<TicketDto> tickets = lottoMachine.generateTickets(new Budget(budget));
            this.holdFlag = false;
            return tickets;
        } catch (RuntimeException e) {
            view.printError(e);
        }
        return null;
    }

    private LottoResultDto getResult() {
        try {
            List<Number> numbers = view.getNumbers().stream().map(Number::new).collect(Collectors.toList());
            Number bonusNumber = new Number(view.getBonusNumber());
            LottoResultDto lottoResultDto = lottoMachine.getResult(new WinningNumbers(numbers, bonusNumber));
            this.holdFlag = false;
            return lottoResultDto;
        } catch (RuntimeException e) {
            view.printError(e);
        }
        return null;
    }

    private void initNumberPool() {
        this.numberPool = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numberPool.add(new Number(i));
        }
    }
}
