package lotto;

import java.util.ArrayList;
import java.util.List;

public class Buyer {

	private final List<Lotto> tickets;

	Buyer(List<Lotto> tickets) {
		this.tickets = List.copyOf(tickets);
	}

	public static Buyer buyLotteries(int budget) {
		List<Lotto> temp = new ArrayList<>();
		int cnt = budget / 1_000;
		if (cnt == 0) {
			throw new IllegalArgumentException("한 개의 로또도 살 수 없는 돈입니다.");
		}
		while (cnt-- > 0) {
			temp.add(Lotto.createRandomLotto());
		}
		return new Buyer(temp);
	}

	public List<Lotto> getTickets() {
		return this.tickets;
	}
}
