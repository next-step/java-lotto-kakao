package lotto;

import java.util.List;

public class User {
	private final Money money;
	private final LottoResult result;
	private final long manualCount;
	private Lottos lottos;

	public User(String price) {
		this.money = new Money(Long.parseLong(price));
		this.manualCount = 0;
		lottos = new Lottos(this.money.getTicketCount());
		result = new LottoResult();
	}

	public User(String price, Lottos maualLottos) {
		this.money = new Money(Long.parseLong(price));
		this.manualCount = maualLottos.getLottoCount();
		this.lottos = maualLottos;
		if (this.manualCount > this.money.getTicketCount()) {
			throw new IllegalArgumentException("수동 로또의 개수는 전체 개수보다 많을 수 없습니다.");
		}
		lottos.addRandomLotto(this.money.getTicketCount() - this.manualCount);
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

	public long getAward() {
		return result.getTotalAward();
	}

	public long getManualCount() {
		return this.manualCount;
	}

	public long getAutoCount() {
		return this.lottos.getLottoCount() - manualCount;
	}
}

