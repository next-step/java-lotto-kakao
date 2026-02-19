package lotto;

import java.util.List;

public class LottoController {
	private final LottoView view;
	private final Parser parser;

	public LottoController(LottoView view, Parser parser) {
		this.view = view;
		this.parser = parser;
	}

	public void run() {
		User user = getUser();
		view.printPurchasedLotto(user.getLottos());

		Lotto answer = getAnswerLotto();
		user.calculateAward(answer);
		view.printResult(user);
	}

	private Lotto getAnswerLotto() {
		try {
			String previousLottoLine = view.readPreviousLotto();
			List<Ball> balls = parser.parse(previousLottoLine);
			String previousBonus = view.readPreviousBonusBall();
			return new Lotto(balls, new Ball(previousBonus));
		} catch (IllegalArgumentException e) {
			view.print(e.getMessage());
			return getAnswerLotto();
		}
	}

	private User getUser() {
		try {
			String price = view.readPrice();
			String manualCount = view.readManualCount();
			Lottos manualLottos = view.readManualLottos(manualCount, parser);
			return new User(price, manualLottos);
		} catch (IllegalArgumentException e) {
			view.print(e.getMessage());
			return getUser();
		}
	}
}
