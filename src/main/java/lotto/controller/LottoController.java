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
import lotto.view.InputView;
import lotto.view.OutputView;

@RequiredArgsConstructor
public class LottoController {
	private static final int MAX_INPUT_RETRIES = 5;

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
		for (int attempt = 1; attempt <= MAX_INPUT_RETRIES; attempt++) {
			try {
				int amount = inputView.readPurchaseAmount();
				purchasePolicy.calculateRandomCountFromAmount(amount, 0);
				return amount;
			} catch (IllegalArgumentException exception) {
				outputView.printError(exception.getMessage());
			}
		}
		throw new IllegalStateException(
			String.format("입력 재시도 횟수(%d회)를 초과했습니다.", MAX_INPUT_RETRIES));
	}

	private int readManualCount(int amount) {
		for (int attempt = 1; attempt <= MAX_INPUT_RETRIES; attempt++) {
			try {
				int manualCount = inputView.readManualCount();
				purchasePolicy.calculateRandomCountFromAmount(amount, manualCount);
				return manualCount;
			} catch (IllegalArgumentException exception) {
				outputView.printError(exception.getMessage());
			}
		}
		throw new IllegalStateException(
			String.format("입력 재시도 횟수(%d회)를 초과했습니다.", MAX_INPUT_RETRIES));
	}

	private LottoGenerator readManualGenerator(int manualCount) {
		for (int attempt = 1; attempt <= MAX_INPUT_RETRIES; attempt++) {
			try {
				List<List<Integer>> manualNumbers = inputView.readManualNumbers(manualCount);
				return new ManualLottoGenerator(manualNumbers);
			} catch (IllegalArgumentException exception) {
				outputView.printError(exception.getMessage());
			}
		}
		throw new IllegalStateException(
			String.format("입력 재시도 횟수(%d회)를 초과했습니다.", MAX_INPUT_RETRIES));
	}

	private Lotto readWinningLotto() {
		for (int attempt = 1; attempt <= MAX_INPUT_RETRIES; attempt++) {
			try {
				return Lotto.from(inputView.readWinningNumbers());
			} catch (IllegalArgumentException exception) {
				outputView.printError(exception.getMessage());
			}
		}
		throw new IllegalStateException(
			String.format("입력 재시도 횟수(%d회)를 초과했습니다.", MAX_INPUT_RETRIES));
	}

	private WinningNumbers readWinningNumbers(Lotto winningLotto) {
		for (int attempt = 1; attempt <= MAX_INPUT_RETRIES; attempt++) {
			try {
				LottoNumber bonusNumber = LottoNumber.from(inputView.readBonusNumber());
				return WinningNumbers.of(winningLotto, bonusNumber);
			} catch (IllegalArgumentException exception) {
				outputView.printError(exception.getMessage());
			}
		}
		throw new IllegalStateException(
			String.format("입력 재시도 횟수(%d회)를 초과했습니다.", MAX_INPUT_RETRIES));
	}
}
