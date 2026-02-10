package lotto;

public enum MatchCount {

	THREE(3, false),
	FOUR(4, false),
	FIVE(5, false),
	FIVE_BONUS(5, true),
	SIX(6, false);

	private final int count;
	private final boolean bonus;

	MatchCount(int count, boolean bonus) {
		this.count = count;
		this.bonus = bonus;
	}

	public int getCount() {
		return count;
	}

	public boolean hasBonus() {
		return bonus;
	}
}

