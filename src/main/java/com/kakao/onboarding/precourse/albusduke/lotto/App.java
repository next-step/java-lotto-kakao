package com.kakao.onboarding.precourse.albusduke.lotto;

import com.kakao.onboarding.precourse.albusduke.lotto.controller.LottoController;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoGames;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseGameAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.RandomLottoNumbersGenerator;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.WinningNumbers;
import com.kakao.onboarding.precourse.albusduke.lotto.service.LottoService;
import com.kakao.onboarding.precourse.albusduke.lotto.service.StatisticsService;
import com.kakao.onboarding.precourse.albusduke.lotto.util.Console;
import com.kakao.onboarding.precourse.albusduke.lotto.view.InputView;
import com.kakao.onboarding.precourse.albusduke.lotto.view.OutputView;

public class App {
	public static void main(String[] args) {
		LottoController lottoController = createLottoController();
		runLottoGame(lottoController);
	}

	private static LottoController createLottoController() {
		Console console = new Console();

		OutputView outputView = new OutputView(console);
		InputView inputView = new InputView(console);

		LottoService lottoService = new LottoService(new RandomLottoNumbersGenerator());
		StatisticsService statisticsService = new StatisticsService();

		return new LottoController(inputView, outputView, lottoService, statisticsService);
	}

	private static void runLottoGame(LottoController lottoController) {
		PurchaseGameAmount purchaseGameAmount = lottoController.calculatePurchaseGameAmount();
		LottoGames lottoGames = lottoController.purchaseLottoGame(purchaseGameAmount);
		WinningNumbers winningNumbers = lottoController.createWinningNumbers();
		lottoController.calculateStatistics(winningNumbers, lottoGames);
	}
}
