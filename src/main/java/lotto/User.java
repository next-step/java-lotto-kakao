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
	private int manualCount;
	private int autoCount;

	public User(Money money, LottoList lottos) {
		this.money = money;
		this.lottos = lottos;
		this.manualCount = 0;
		this.autoCount = 0;
	}

	public void setLottoCount(int manualCount, int autoCount) {
		this.manualCount = manualCount;
		this.autoCount = autoCount;
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

	public int getManualCount() {
		return manualCount;
	}

	public int getAutoCount() {
		return autoCount;
	}
}
