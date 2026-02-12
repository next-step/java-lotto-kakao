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

		AnswerLotto answer = getAnswerLotto();
		user.calculateAward(answer);
		view.printResult(user);
	}

	private AnswerLotto getAnswerLotto() {
		try {
			String previousLottoLine = view.readPreviousLotto();
			List<Ball> balls = parser.parse(previousLottoLine);

			String previousBonus = view.readPreviousBonusBall();
			Ball bonus = new Ball(previousBonus);

			return new AnswerLotto(balls, bonus);
		} catch (IllegalArgumentException e) {
			view.print(e.getMessage());
			return getAnswerLotto();
		}
	}

	private User getUser() {
		try {
			String price = view.readPrice();
			return new User(price);
		} catch (IllegalArgumentException e) {
			view.print(e.getMessage());
			return getUser();
		}
	}
}
