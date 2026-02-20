package lotto;

import lotto.controller.LottoController;
import lotto.domain.DefaultLottoGeneratorFactory;
import lotto.domain.LottoPurchasePolicy;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		try {
			LottoController controller = new LottoController(
				new InputView(),
				new OutputView(),
				new LottoPurchasePolicy(),
				new DefaultLottoGeneratorFactory()
			);
			controller.run();
		} catch (RuntimeException exception) {
			System.err.println("[ERROR] 시스템 오류가 발생했습니다.");
			throw exception;
		}
	}
}
