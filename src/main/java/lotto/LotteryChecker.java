package lotto;

import java.util.EnumMap;
import java.util.Map;

public class LotteryChecker {

	private final Map<MatchCount, Integer> counts = new EnumMap<>(MatchCount.class);

	public LotteryChecker() {
		for (MatchCount match : MatchCount.values()) {
			counts.put(match, 0);
		}
	}

	public void increase(MatchCount matchCount) {
		counts.put(matchCount, counts.get(matchCount) + 1);
	}

	public int getCount(MatchCount matchCount) {
		return counts.get(matchCount);
	}

	public int calculateTotalPrize() {
		int sum = 0;
		for (MatchCount match : MatchCount.values()) {
			sum += counts.get(match) * match.getPrice();
		}
		return sum;
	}

	public Map<MatchCount, Integer> getCounts() {
		return this.counts;
	}

	public static double calculateReturnRate(int totalPrize, int totalPurchaseCost) {
		double rate = (double) totalPrize / totalPurchaseCost;
		return Math.round(rate * 100) / 100.0;
	}


	/* private int threeHits;
	private int fourHits;
	private int fiveHits;
	private int bonuses;
	private int sixHits;

	LotteryChecker() {
		this.threeHits = 0;
		this.fourHits = 0;
		this.fiveHits = 0;
		this.bonuses = 0;
		this.sixHits = 0;
	}

	void increaseThreeHits() {
		threeHits++;
	}

	void increaseFourHits() {
		fourHits++;
	}

	void increaseFiveHits() {
		fiveHits++;
	}

	void increaseBonusHits() {
		bonuses++;
	}

	void increaseSixHits() {
		sixHits++;
	}

	int getThreeHits() {
		return threeHits;
	}

	int getFourHits() {
		return fourHits;
	}

	int getFiveHits() {
		return fiveHits;
	}

	int getBonusesHits() {
		return bonuses;
	}

	int getSixHits() {
		return sixHits;
	}


	 */
}
