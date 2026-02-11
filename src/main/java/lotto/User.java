package lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.enums.LottoStatus;

public class User {
	private final Money money;
	private final Map<LottoStatus, Integer> result = new HashMap<>();
	private List<Lotto> lottos;
	private long award;

	public User(String input) {
		this.money = new Money(Long.parseLong(input));
		lottos = new ArrayList<>();
		for (int i = 0; i < this.money.getTicketCount(); i++) {
			lottos.add(new Lotto());
		}
	}

	public void calculateAward(Lotto answerLotto) {
		for (Lotto lotto : lottos) {
			lotto.check(answerLotto);
			award += lotto.getStatus().getMoney();
			result.put(lotto.getStatus(), result.getOrDefault(lotto.getStatus(), 0) + 1);
		}
	}

	public Map<LottoStatus, Integer> getResult() {
		return result;
	}

	public long getPrice() {
		return this.money.getPrice();
	}

	public List<Lotto> getLottos() {
		return this.lottos;
	}

	public void setLottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public long getAward() {
		return this.award;
	}
}

