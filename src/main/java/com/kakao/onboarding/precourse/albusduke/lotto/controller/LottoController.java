package com.kakao.onboarding.precourse.albusduke.lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.*;
import com.kakao.onboarding.precourse.albusduke.lotto.service.LottoService;
import com.kakao.onboarding.precourse.albusduke.lotto.service.StatisticsService;
import com.kakao.onboarding.precourse.albusduke.lotto.view.InputView;
import com.kakao.onboarding.precourse.albusduke.lotto.view.OutputView;

public class LottoController {

    private final InputView inputConsoleView;
    private final OutputView outputConsoleView;

    private final LottoService lottoService;
    private final StatisticsService statisticsService;

    public LottoController(InputView inputConsoleView, OutputView outputConsoleView, LottoService lottoService, StatisticsService statisticsService) {
        this.inputConsoleView = inputConsoleView;
        this.outputConsoleView = outputConsoleView;
        this.lottoService = lottoService;
        this.statisticsService = statisticsService;
    }

    public void runLottoGame() {
        TicketQuantity ticketQuantity = calculateTicketQuantity();
        LottoGames lottoGames = purchaseLottoGames(ticketQuantity);
        outputConsoleView.outputPurchaseCount(ticketQuantity.getManualQuantity(), ticketQuantity.getRandomQuantity());
        outputConsoleView.outputLottoNumbers(lottoGames);
        WinningNumbers winningNumbers = createWinningNumbers();
        calculateStatistics(winningNumbers, lottoGames);
    }

    public TicketQuantity calculateTicketQuantity() {
        try {
            PurchaseAmount purchaseAmount = inputConsoleView.inputPurchaseAmount();
            Money money = lottoService.createMoneyBy(purchaseAmount);
            ManualTicketQuantity manualTicketQuantity = inputConsoleView.inputManualTicketQuantity();
            TicketQuantity ticketQuantity = lottoService.calculateTicketQuantity(money, manualTicketQuantity);
            return ticketQuantity;
        } catch (IllegalArgumentException e) {
            outputConsoleView.outputError(e);
            return calculateTicketQuantity();
        }
    }

    private LottoGames purchaseLottoGames(TicketQuantity ticketQuantity) {
        List<LottoNumbers> manualLottoNumbers = purchaseManualLottoGames(ticketQuantity);
        List<LottoNumbers> randomLottoNumbers = purchaseRandomLottoGames(ticketQuantity);
        List<LottoNumbers> allLottoNumbers = new ArrayList<>(manualLottoNumbers);
        allLottoNumbers.addAll(randomLottoNumbers);
        return new LottoGames(allLottoNumbers);
    }

    private List<LottoNumbers> purchaseRandomLottoGames(TicketQuantity ticketQuantity) {
        return lottoService.purchaseRandomLottoGames(ticketQuantity);
    }

    private List<LottoNumbers> purchaseManualLottoGames(TicketQuantity ticketQuantity) {
        try {
            outputConsoleView.OutputManualLottoNumbersPrompt();
            return IntStream.range(0, ticketQuantity.getManualQuantity())
                .mapToObj(i -> purchaseManualLottoGame(ticketQuantity))
                .toList();
        } catch (IllegalArgumentException e) {
            outputConsoleView.outputError(e);
            return purchaseManualLottoGames(ticketQuantity);
        }
    }

    public LottoNumbers purchaseManualLottoGame(TicketQuantity ticketQuantity) {
        try {
            ManualTicketNumbers manualTicketNumbers = inputConsoleView.inputManualTicketNumbers();
            return lottoService.purchaseManualLottoGame(manualTicketNumbers);
        } catch (IllegalArgumentException e) {
            outputConsoleView.outputError(e);
            return purchaseManualLottoGame(ticketQuantity);
        }
    }

    public WinningNumbers createWinningNumbers() {
        try {
            return inputConsoleView.inputWinningNumbers();
        } catch (IllegalArgumentException e) {
            outputConsoleView.outputError(e);
            return createWinningNumbers();
        }
    }

    public void calculateStatistics(WinningNumbers winningNumbers, LottoGames lottoGames) {
        Statistics statistics = statisticsService.calculateStatistics(winningNumbers, lottoGames);
        outputConsoleView.outputStatistics(statistics);
    }
}
