package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

	private final LottoService lottoService;

	public LottoGame() {
		this.lottoService = new LottoService();
	}

	public void run() {
		try {
			Money money = new Money(InputView.readPurchaseAmount());
			LottoBundles lottos = buyLotto(money);
			WinningLotto winningLotto = makeWinningLotto();

			processResult(lottos, winningLotto, money);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			run(); // 예외 발생 시 재시도 로직
		}
	}

	private LottoBundles buyLotto(Money money) {
		LottoPurchaseAmount amount = new LottoPurchaseAmount(money, InputView.readManualCount());
		return new LottoBundles(buyManualLottos(amount), buyAutoLottos(amount));
	}

	private LottoBundle buyManualLottos(LottoPurchaseAmount amount) {
		int count = amount.getManualCount();
		return new LottoBundle(askManualLottos(count));
	}

	private List<Lotto> askManualLottos(int count) {
		List<Lotto> manualLottos = new ArrayList<>();
		OutputView.purchaseManual();
		for (int i = 0; i < count; i++) {
			manualLottos.add(repeatUntilSuccess(this::createManualLotto));
		}
		return manualLottos;
	}

	private Lotto createManualLotto() {
		String input = InputView.readManualNumbers();
		List<LottoNumber> lottoNumbers = LottoParser.parseLottoNumbers(input);
		return new Lotto(lottoNumbers);
	}

	private LottoBundle buyAutoLottos(LottoPurchaseAmount amount) {
		int count = amount.getAutoCount();
		OutputView.printPurchaseCount(amount);
		LottoBundle bundle = lottoService.purchaseAuto(count);
		OutputView.printLottoBundle(bundle);
		return bundle;
	}

	private void processResult(LottoBundles lottos, WinningLotto winningLotto, Money money) {
		LottoResult lottoResult = lottos.matchAll(winningLotto);

		OutputView.printStatisticsHeader();
		OutputView.printResult(lottoResult);
		OutputView.printYield(lottoResult.calculateYield(money));

	}

	private WinningLotto makeWinningLotto() {
		List<LottoNumber> winningNumbers = LottoParser.parseLottoNumbers(
			InputView.readWinningNumbers());
		Lotto lotto = new Lotto(winningNumbers);
		LottoNumber bonusNumber = new LottoNumber(LottoParser.parseBonusNumber(
			InputView.readingBonusNumber()));

		return new WinningLotto(lotto, bonusNumber);
	}

	private <T> T repeatUntilSuccess(Supplier<T> supplier) {
		try {
			return supplier.get();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return repeatUntilSuccess(supplier);
		}
	}

}
