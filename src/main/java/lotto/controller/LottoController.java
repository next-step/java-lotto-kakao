package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Budget;
import lotto.domain.LottoMachine;
import lotto.domain.dto.LottoResultDto;
import lotto.domain.LottoNumber;
import lotto.domain.dto.TicketDto;
import lotto.domain.WinningNumbers;
import lotto.view.LottoView;

public class LottoController {
    private final LottoMachine lottoMachine;
    private final LottoView view;
    private List<LottoNumber> lottoNumberPool;
    private boolean holdFlag;

    public LottoController() {
        initNumberPool();
        this.lottoMachine = new LottoMachine(lottoNumberPool);
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
            List<LottoNumber> lottoNumbers = view.getNumbers().stream().map(LottoNumber::new).collect(Collectors.toList());
            LottoNumber bonusLottoNumber = new LottoNumber(view.getBonusNumber());
            LottoResultDto lottoResultDto = lottoMachine.getResult(new WinningNumbers(lottoNumbers, bonusLottoNumber));
            this.holdFlag = false;
            return lottoResultDto;
        } catch (RuntimeException e) {
            view.printError(e);
        }
        return null;
    }

    private void initNumberPool() {
        this.lottoNumberPool = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumberPool.add(new LottoNumber(i));
        }
    }
}
