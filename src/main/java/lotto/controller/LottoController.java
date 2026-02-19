package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatistics;
import lotto.domain.WinningNumbers;
import lotto.exception.LottoException;
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
		int amount = readPurchaseAmount();
		int manualCount = readManualCount(amount);
		int autoCount = lottoMachine.calculateRandomCountFromAmount(amount, manualCount);
		List<Lotto> manualLottos = readManualLottos(manualCount);
		List<Lotto> autoLottos = lottoMachine.issueRandom(autoCount);

		List<Lotto> lottos = new ArrayList<>();
		lottos.addAll(manualLottos);
		lottos.addAll(autoLottos);
		outputView.printLottos(lottos, manualCount, autoCount);

		Lotto winningLotto = readWinningLotto();
		WinningNumbers winning = readWinningNumbers(winningLotto);
		LottoStatistics statistics = LottoStatistics.of(lottos, winning);

		outputView.printStatistics(statistics);
		outputView.printProfitRate(statistics.getProfitRate(amount));
	}

	private int readPurchaseAmount() {
		while (true) {
			try {
				int amount = inputView.readPurchaseAmount();
				lottoMachine.calculateRandomCountFromAmount(amount, 0);
				return amount;
			} catch (LottoException exception) {
				outputView.printError(exception.getMessage());
			}
		}
	}

	private int readManualCount(int amount) {
		while (true) {
			try {
				int manualCount = inputView.readManualCount();
				lottoMachine.calculateRandomCountFromAmount(amount, manualCount);
				return manualCount;
			} catch (LottoException exception) {
				outputView.printError(exception.getMessage());
			}
		}
	}

	private List<Lotto> readManualLottos(int manualCount) {
		while (true) {
			try {
				List<List<Integer>> manualNumbers = inputView.readManualNumbers(manualCount);
				return lottoMachine.issueManual(manualNumbers);
			} catch (LottoException exception) {
				outputView.printError(exception.getMessage());
			}
		}
	}

	private Lotto readWinningLotto() {
		while (true) {
			try {
				return Lotto.from(inputView.readWinningNumbers());
			} catch (LottoException exception) {
				outputView.printError(exception.getMessage());
			}
		}
	}

	private WinningNumbers readWinningNumbers(Lotto winningLotto) {
		while (true) {
			try {
				LottoNumber bonusNumber = LottoNumber.from(inputView.readBonusNumber());
				return WinningNumbers.of(winningLotto, bonusNumber);
			} catch (LottoException exception) {
				outputView.printError(exception.getMessage());
			}
		}
	}
}
