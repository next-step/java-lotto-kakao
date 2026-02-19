package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
	private final LottoView view;
	private final Parser parser;

	public LottoController(LottoView view, Parser parser) {
		this.view = view;
		this.parser = parser;
	}

	public void run() {
		Money money = getMoney();
		User user = getUser(money);
		view.printPurchasedLotto(user.getLottos(), user.getManualCount(), user.getAutoCount());

		AnswerLotto answer = getAnswerLotto();
		user.calculateAward(answer);
		view.printResult(user);
	}

	private AnswerLotto getAnswerLotto() {
		try {
			String previousLottoLine = view.readPreviousLotto();
			List<Ball> balls = parser.parse(previousLottoLine);
			Ball bonus = parser.parseBall(view.readPreviousBonusBall());
			return new AnswerLotto(balls, bonus);
		} catch (IllegalArgumentException e) {
			view.print(e.getMessage());
			return getAnswerLotto();
		}
	}

	private Money getMoney() {
		try {
			String price = view.readPrice();
			return new Money(price);
		} catch (IllegalArgumentException e) {
			view.print(e.getMessage());
			return getMoney();
		}
	}

	private User getUser(Money money) {
		try {
			int manualCount = getManualLottoCount(money);
			int autoCount = (int)(money.getPrice() / 1000 - manualCount);
			LottoList lottos = generateLottos(manualCount, autoCount);
			User user = new User(money, lottos);
			user.setLottoCount(manualCount, autoCount);
			return user;
		} catch (IllegalArgumentException e) {
			view.print(e.getMessage());
			return getUser(money);
		}
	}

	private LottoList generateLottos(int manualCount, int autoCount) {
		List<List<Ball>> manualBalls = getManualLottos(manualCount);
		LottoGenerator generator = createCombinedGenerator(manualBalls, autoCount);
		return generator.generate();
	}

	private int getManualLottoCount(Money money) {
		try {
			String manualLottoCountLine = view.readManualLottoCount();
			int manualCount = Integer.parseInt(manualLottoCountLine.trim());
			money.validateManualLottoCount(manualCount);
			return manualCount;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("수동 로또 개수는 숫자여야 합니다.");
		}
	}

	private List<List<Ball>> getManualLottos(int count) {
		try {
			List<String> manualLottoLines = view.readManualLottos(count);
			return parser.parseManualLottos(manualLottoLines);
		} catch (IllegalArgumentException e) {
			view.print(e.getMessage());
			return getManualLottos(count);
		}
	}

	private LottoGenerator createCombinedGenerator(List<List<Ball>> manualBalls, int autoCount) {
		List<LottoGenerator> generators = new ArrayList<>();
		if (!manualBalls.isEmpty()) {
			generators.add(new ManualLottoGenerator(manualBalls));
		}
		if (autoCount > 0) {
			generators.add(new AutoLottoGenerator(autoCount));
		}

		return new CompositeLottoGenerator(generators);
	}
}
