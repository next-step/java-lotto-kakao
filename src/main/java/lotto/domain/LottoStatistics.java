package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class LottoStatistics {
	private final Map<LottoResult, Integer> counts;
	private final double profitRate;

	private LottoStatistics(Map<LottoResult, Integer> counts, double profitRate) {
		this.counts = counts;
		this.profitRate = profitRate;
	}

	public static LottoStatistics of(LottoPurchase purchase, WinningNumbers winningNumbers) {
		Map<LottoResult, Integer> counts = initializeCounts();
		for (Lotto lotto : purchase.getLottos()) {
			winningNumbers.match(lotto)
				.ifPresent(result -> counts.put(result, counts.get(result) + 1));
		}
		long totalPrize = calculateTotalPrize(counts);
		double profitRate = totalPrize / (double)purchase.getAmount();
		return new LottoStatistics(counts, profitRate);
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
}
