package lotto.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatistics;
import lotto.domain.WinningNumbers;
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
		List<Lotto> lottos = lottoMachine.issue(amount);
		outputView.printLottos(lottos);

		Lotto winningNumbers = Lotto.from(inputView.readWinningNumbers());
		LottoNumber bonusNumber = LottoNumber.from(inputView.readBonusNumber());
		WinningNumbers winning = WinningNumbers.of(winningNumbers, bonusNumber);
		LottoStatistics statistics = LottoStatistics.of(lottos, winning);

		outputView.printStatistics(statistics);
		outputView.printProfitRate(statistics.getProfitRate(amount));
	}
}
