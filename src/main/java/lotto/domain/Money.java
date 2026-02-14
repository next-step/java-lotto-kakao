package lotto.domain;

public class Money {
	private final long money;
	private static final int PRICE_UNIT = 1000;

	public Money(long money) {
		validateUnit(money);
		this.money = money;
	}

	public long getMoney() {
		return money;
	}

	public int calculateLottoCount() {
		return (int)money / PRICE_UNIT;
	}

	private void validateUnit(long money) {
		if (money % PRICE_UNIT != 0) {
			throw new IllegalArgumentException("천원 단위로만 입력이 가능합니다.");
		}
	}
}
