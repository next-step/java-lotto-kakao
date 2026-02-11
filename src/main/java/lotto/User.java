package lotto;

import java.util.List;

public class User {
	private final Money money;
	private final LottoResult result;
	private Lottos lottos;

	public User(String input) {
		this.money = new Money(Long.parseLong(input));
		lottos = new Lottos(this.money.getTicketCount());
		result = new LottoResult();
	}

	public void calculateAward(Lotto answerLotto) {
		for (Lotto lotto : lottos.getLottoList()) {
			lotto.check(answerLotto);
			result.add(lotto);
		}
	}

	public LottoResult getResult() {
		return result;
	}

	public long getPrice() {
		return this.money.getPrice();
	}

	public List<Lotto> getLottos() {
		return this.lottos.getLottoList();
	}

	public void setLottos(List<Lotto> lottos) {
		this.lottos.setLottoList(lottos);
	}

	public long getAward() {
		return result.getTotalAward();
	}
}

