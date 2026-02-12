package lotto.enums;

import java.util.Map;

public enum LottoStatus {
	MISS(0, 0),
	THREE(3, 5_000),
	FOUR(4, 50_000),
	FIVE(5, 1_500_000),
	SIX_BONUS(5, 30_000_000),
	SIX(6, 2_000_000_000);

	private static final Map<Integer, LottoStatus> BY_COUNT = Map.of(
		3, THREE,
		4, FOUR,
		5, FIVE,
		6, SIX
	);
	private final int count;
	private final long money;

	LottoStatus(int count, long money) {
		this.count = count;
		this.money = money;
	}

	public static LottoStatus of(int count, boolean hasBonus) {
		if (count == 5 && hasBonus)
			return SIX_BONUS;
		return BY_COUNT.getOrDefault(count, MISS);
	}

	public int getCount() {
		return count;
	}

	public long getMoney() {
		return money;
	}
}
