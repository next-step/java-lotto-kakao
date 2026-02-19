package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class Buyer {

	private final List<Lotto> tickets;

	Buyer(List<Lotto> tickets) {
		this.tickets = List.copyOf(tickets);
	}

	public static Buyer buyLotteries(PurchasePlan plan, List<Lotto> manualTickets) {

		if (manualTickets == null) {
			throw new IllegalArgumentException("수동 구매 목록은 null일 수 없습니다.");
		}

		if (manualTickets.size() != plan.manualCount()) {
			throw new IllegalArgumentException("수동 구매가 올바르게 완료되지 않았습니다.");

		}

		List<Lotto> tickets = new ArrayList<>(manualTickets);
		for (int i = 0; i < plan.autoCount(); i++) {
			tickets.add(Lotto.createRandomLotto());
		}

		return new Buyer(tickets);
	}

	public List<Lotto> getTickets() {
		return this.tickets;
	}
}
