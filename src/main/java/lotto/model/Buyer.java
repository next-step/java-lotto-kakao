package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class Buyer {

	private final List<Lotto> tickets;
	private final static int LOTTO_PRICE = 1_000;

	Buyer(List<Lotto> tickets) {
		this.tickets = List.copyOf(tickets);
	}

	public static Buyer buyLotteries(Money money) {
		List<Lotto> tickets = new ArrayList<>();
		int amount = money.amount() / LOTTO_PRICE;
		while (amount-- > 0) {
			tickets.add(Lotto.createRandomLotto());
		}
		return new Buyer(tickets);
	}

	public List<Lotto> getTickets() {
		return this.tickets;
	}
}
