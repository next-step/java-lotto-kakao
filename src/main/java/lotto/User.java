package lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.enums.LottoStatus;

public class User {
	private static final long TICKET_COST = 1000;
	private final long price;
	private List<Lotto> lottos;
	private long award;
	;
	private Map<LottoStatus, Integer> result = new HashMap<>();

	public User(String input) {
		long inputPrice = Long.parseLong(input);
		if (inputPrice <= 0 || inputPrice % TICKET_COST != 0) {
			throw new IllegalArgumentException("잘못된 구입 금액입니다.");
		}
		this.price = inputPrice;
		lottos = new ArrayList<>();
		for (int i = 0; i < price / TICKET_COST; i++) {
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
		return price;
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

