package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.enums.LottoStatus;

public class User {
	private static final long TICKET_COST = 1000;

	private final Money money;
	private final Map<LottoStatus, Integer> result = new HashMap<>();
	private LottoList lottos;
	private long award;

	public User(String input) {
		long inputPrice = Long.parseLong(input);
		if (inputPrice <= 0 || inputPrice % TICKET_COST != 0) {
			throw new IllegalArgumentException("잘못된 구입 금액입니다.");
		}
		this.money = new Money(inputPrice);

		lottos = new LottoList();
		for (int i = 0; i < money.getPrice() / TICKET_COST; i++) {
			lottos.addLotto(new Lotto());
		}
	}

	public void calculateAward(AnswerLotto answerLotto) {
		award = 0;
		result.clear();

		for (Lotto lotto : lottos.getLottos()) {
			LottoStatus status = lotto.check(answerLotto);
			award += status.getMoney();
			result.put(status, result.getOrDefault(status, 0) + 1);
		}
	}

	public Map<LottoStatus, Integer> getResult() {
		return result;
	}

	public long getPrice() {
		return money.getPrice();
	}

	public List<Lotto> getLottos() {
		return lottos.getLottos();
	}

	public void setLottos(List<Lotto> lottos) {
		this.lottos.setLottos(lottos);
	}

	public long getAward() {
		return this.award;
	}
}
