package com.kakao.onboarding.precourse.albusduke.lotto.controller;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoGames;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseGameAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.Statistics;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.WinningNumbers;
import com.kakao.onboarding.precourse.albusduke.lotto.service.LottoService;
import com.kakao.onboarding.precourse.albusduke.lotto.service.StatisticsService;
import com.kakao.onboarding.precourse.albusduke.lotto.view.InputConsoleView;
import com.kakao.onboarding.precourse.albusduke.lotto.view.OutputConsoleView;

public class LottoController {

	private final InputConsoleView inputConsoleView;
	private final OutputConsoleView outputConsoleView;

	private final LottoService lottoService;
	private final StatisticsService statisticsService;

	public LottoController(InputConsoleView inputConsoleView, OutputConsoleView outputConsoleView,
		LottoService lottoService, StatisticsService statisticsService) {
		this.inputConsoleView = inputConsoleView;
		this.outputConsoleView = outputConsoleView;
		this.lottoService = lottoService;
		this.statisticsService = statisticsService;
	}

	public PurchaseGameAmount calculatePurchaseGameAmount() {
		try {
			PurchaseAmount purchaseAmount = inputConsoleView.inputPurchaseAmount();
			PurchaseGameAmount purchaseGameAmount = lottoService.purchaseLottoGames(purchaseAmount);
			outputConsoleView.outputPurchaseGameAmount(purchaseGameAmount);
			return purchaseGameAmount;
		} catch (IllegalArgumentException e) {
			outputConsoleView.outputError(e);
			return calculatePurchaseGameAmount();
		}
	}

	public LottoGames purchaseLottoGame(PurchaseGameAmount purchaseAmount) {
		try {
			LottoGames lottoGames = lottoService.purchaseLottoGame(purchaseAmount);
			outputConsoleView.outputLottoNumbers(lottoGames);
			return lottoGames;
		} catch (IllegalArgumentException e) {
			outputConsoleView.outputError(e);
			return purchaseLottoGame(purchaseAmount);
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
