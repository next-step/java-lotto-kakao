package lotto.model;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum MatchCount {

	NOTHING(0, false, 0),
	THREE(3, false, 5000),
	FOUR(4, false, 50000),
	FIVE(5, false, 1500000),
	FIVE_BONUS(5, true, 30000000),
	SIX(6, false, 2000000000);

	private final int requiresCount;
	private final boolean requiresBonus;
	private final int price;

	private static final int BONUS_MATCH_COUNT = 5;

	private static final Map<Key, MatchCount> LOOKUP = Arrays.stream(values())
		.collect(
			Collectors.toMap(
				m -> new Key(m.requiresCount, m.requiresBonus),
				m -> m
			)
		);

	MatchCount(int requiresCount, boolean requiresBonus, int price) {
		this.requiresCount = requiresCount;
		this.requiresBonus = requiresBonus;
		this.price = price;
	}

	public static MatchCount from(int count, boolean hasBonus) {
		boolean normalizedBonus = hasBonus && count == BONUS_MATCH_COUNT;
		return LOOKUP.getOrDefault(new Key(count, normalizedBonus), NOTHING);
	}

	public String statisticLine(int winnerCount) {
		return conditionText() + "(" + price + "원) - " + winnerCount + "개";
	}

	private String conditionText() {
		if (isBonusRequired()) {
			return requiredMatchCount() + "개 일치, 보너스 볼 일치";
		}
		return requiredMatchCount() + "개 일치";
	}

	public boolean isWinningRank() {
		return price > 0;
	}

	public int requiredMatchCount() {
		return requiresCount;
	}

	public boolean isBonusRequired() {
		return requiresBonus;
	}

	public int getPrice() {
		return price;
	}

	private record Key(int count, boolean bonus) {
	}

}
