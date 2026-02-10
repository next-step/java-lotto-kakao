package lotto;

public enum MatchCount {

	THREE(3, false, 5000),
	FOUR(4, false, 50000),
	FIVE(5, false, 1500000),
	FIVE_BONUS(5, true, 30000000),
	SIX(6, false, 2000000000);

	private final int count;
	private final boolean bonus;
	private final int price;

	MatchCount(int count, boolean bonus, int price) {
		this.count = count;
		this.bonus = bonus;
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public boolean hasBonus() {
		return bonus;
	}

	public int getPrice() {
		return price;
	}

	public static MatchCount from(int count, boolean bonus) {
		if (count == 6) {
			return SIX;
		}
		if (count == 5 && bonus) {
			return FIVE_BONUS;
		}
		if (count == 5) {
			return FIVE;
		}
		if (count == 4) {
			return FOUR;
		}
		if (count == 3) {
			return THREE;
		}
		return null;
	}

}

