package lotto.controller;

import lombok.RequiredArgsConstructor;
import lotto.domain.LottoMachine;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

@RequiredArgsConstructor
public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;
	private final LottoMachine lottoMachine;

	public static LottoController create() {
		return new LottoController(
			new InputView(),
			new OutputView(),
			new LottoMachine()
		);
	}

	public void run() {
		int amount = inputView.readPurchaseAmount();
		int manualCount = inputView.readManualCount(amount);
		LottoPurchase purchase = lottoMachine.issue(amount, manualCount, inputView.readManualNumbers(manualCount));
		outputView.printLottos(purchase);

		LottoStatistics statistics = LottoStatistics.of(purchase, inputView.readWinningNumbers());
		outputView.printResult(statistics);
	}
}
