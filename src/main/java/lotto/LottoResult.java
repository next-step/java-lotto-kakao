package lotto;

import java.util.HashMap;
import java.util.Map;

import lotto.enums.LottoStatus;

public class LottoResult {
	Map<LottoStatus, Integer> result;
	private long totalAward;

	public LottoResult() {
		this.result = new HashMap<LottoStatus, Integer>();
		for (LottoStatus status : LottoStatus.values()) {
			result.put(status, 0);
		}
	}

	public Map<LottoStatus, Integer> getResult() {
		return result;
	}

	public void add(Lotto lotto) {
		LottoStatus status = lotto.getStatus();
		result.put(status, result.get(status) + 1);
		totalAward += status.getMoney();
	}

	public long getTotalAward() {
		return totalAward;
	}

	public int get(LottoStatus status) {
		return result.getOrDefault(status, 0);
	}
}
