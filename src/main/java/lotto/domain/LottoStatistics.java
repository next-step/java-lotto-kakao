package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lotto.exception.LottoValidationException;

@Getter
public class LottoStatistics {
	private final Map<LottoResult, Integer> counts;
	private final long totalPrize;

	private LottoStatistics(Map<LottoResult, Integer> counts, long totalPrize) {
		this.counts = counts;
		this.totalPrize = totalPrize;
	}

	public static LottoStatistics of(List<Lotto> lottos, WinningNumbers winningNumbers) {
		Map<LottoResult, Integer> counts = initializeCounts();
		for (Lotto lotto : lottos) {
			winningNumbers.match(lotto)
				.ifPresent(result -> counts.put(result, counts.get(result) + 1));
		}
		long totalPrize = calculateTotalPrize(counts);
		return new LottoStatistics(counts, totalPrize);
	}

	private static Map<LottoResult, Integer> initializeCounts() {
		Map<LottoResult, Integer> counts = new EnumMap<>(LottoResult.class);
		for (LottoResult result : LottoResult.values()) {
			counts.put(result, 0);
		}
		return counts;
	}

	private static long calculateTotalPrize(Map<LottoResult, Integer> counts) {
		long total = 0L;
		for (Map.Entry<LottoResult, Integer> entry : counts.entrySet()) {
			long prize = entry.getKey().getPrize();
			int count = entry.getValue();
			total += prize * count;
		}
		return total;
	}

	public double getProfitRate(int purchaseAmount) {
		if (purchaseAmount == 0) {
			throw new LottoValidationException("구입 금액은 0원일 수 없습니다.");
		}
		return totalPrize / (double)purchaseAmount;
	}
}
