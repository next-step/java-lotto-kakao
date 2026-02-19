package lotto.controller;

import lotto.*;
import lotto.model.Buyer;
import lotto.model.LotteryChecker;
import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.LottoNumbersParser;
import lotto.model.MatchCount;
import lotto.model.Money;
import lotto.model.PurchasePlan;
import lotto.model.WinningLotto;
import lotto.view.LottoView;

import java.util.*;

public class LottoController {

	private final LottoView view;
	private final LottoNumbersParser parser = new LottoNumbersParser();

	public LottoController(LottoView view) {
		this.view = view;
	}

	public void run() {
		Money money = view.readMoney();
		int manualCount = view.readManualPurchaseCount();
		PurchasePlan plan = PurchasePlan.of(money, manualCount);
		List<Lotto> manualTickets = readManualTickets(plan.manualCount());
		Buyer buyer = Buyer.buyLotteries(plan, manualTickets);
		List<Lotto> tickets = buyer.getTickets();
		view.printTickets(plan.manualCount(), plan.autoCount(), tickets);

		WinningLotto winningLotto = createWinningLotto();

		LotteryChecker checker = calculateResults(tickets, winningLotto);

		view.printStatistics(
			checker.getCounts(),
			checker.calculateReturnRate(money)
		);
	}

	private WinningLotto createWinningLotto() {
		String input = view.readWinningNumbers();
		Lotto winningLotto = parser.parse(input);

		int bonusInput = view.readBonus();
		LottoNumber bonus = new LottoNumber(bonusInput);

		return new WinningLotto(winningLotto, bonus);
	}

	private List<Lotto> readManualTickets(int manualCount) {
		List<String> manualInputs = view.readManualLottoNumbers(manualCount);
		return manualInputs.stream()
			.map(parser::parse)
			.toList();
	}

	private LotteryChecker calculateResults(List<Lotto> tickets, WinningLotto winningLotto) {
		LotteryChecker checker = new LotteryChecker();

		for (Lotto ticket : tickets) {
			MatchCount match = winningLotto.aggregateMatchCount(ticket);
			checker.increase(match);
		}

		return checker;
	}
}
