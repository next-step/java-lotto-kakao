package lotto.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPurchasePolicy;
import lotto.domain.LottoStatistics;
import lotto.domain.ManualLottoGenerator;
import lotto.domain.RandomLottoGenerator;
import lotto.domain.WinningNumbers;
import lotto.exception.LottoException;
import lotto.view.InputView;
import lotto.view.OutputView;

@RequiredArgsConstructor
public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;
	private final LottoPurchasePolicy purchasePolicy;

	public static LottoController create() {
		return new LottoController(
			new InputView(),
			new OutputView(),
			new LottoPurchasePolicy()
		);
	}

	public void run() {
		int amount = readPurchaseAmount();
		int manualCount = readManualCount(amount);
		int randomCount = purchasePolicy.calculateRandomCountFromAmount(amount, manualCount);
		LottoGenerator manualGenerator = readManualGenerator(manualCount);
		LottoGenerator randomGenerator = new RandomLottoGenerator(randomCount);
		LottoMachine lottoMachine = new LottoMachine(List.of(manualGenerator, randomGenerator));
		List<Lotto> lottos = lottoMachine.issue();

		outputView.printLottos(lottos, manualCount, randomCount);

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
				purchasePolicy.calculateRandomCountFromAmount(amount, 0);
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
				purchasePolicy.calculateRandomCountFromAmount(amount, manualCount);
				return manualCount;
			} catch (LottoException exception) {
				outputView.printError(exception.getMessage());
			}
		}
	}

	private LottoGenerator readManualGenerator(int manualCount) {
		while (true) {
			try {
				List<List<Integer>> manualNumbers = inputView.readManualNumbers(manualCount);
				return new ManualLottoGenerator(manualNumbers);
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
