package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Budget;
import lotto.domain.LottoMachine;
import lotto.domain.dto.LottoResultDto;
import lotto.domain.LottoNumber;
import lotto.domain.dto.TicketDto;
import lotto.domain.WinningLotto;
import lotto.view.LottoView;

public class LottoController {
    private final LottoMachine lottoMachine;
    private final LottoView view;

    public LottoController() {
        this.lottoMachine = new LottoMachine();
        this.view = new LottoView();
    }

    public void start() {
        List<TicketDto> tickets = createTickets();
        view.printTickets(tickets);

        LottoResultDto result = getResult();
        view.printLottoResult(result);
    }

    private List<TicketDto> createTickets() {
        try {
            Budget budget = new Budget(view.getBudget());
            return lottoMachine.generateTickets(budget);
        } catch (RuntimeException e) {
            view.printError(e);
            return createTickets();
        }
    }

    private LottoResultDto getResult() {
        try {
            List<Integer> inputNumbers = view.getNumbers();

            List<LottoNumber> lottoNumbers = inputNumbers.stream()
                    .map(LottoNumber::valueOf)
                    .collect(Collectors.toList());
            LottoNumber bonusLottoNumber = LottoNumber.valueOf(view.getBonusNumber());

            return lottoMachine.getResult(new WinningLotto(lottoNumbers, bonusLottoNumber));
        } catch (RuntimeException e) {
            view.printError(e);
            return getResult();
        }
    }
}
