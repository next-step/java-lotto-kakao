package lotto;

public class Money {
	private static final long TICKET_COST = 1000;
	private long price;

	public Money(String input) {
		long inputPrice = Long.parseLong(input);
		if (inputPrice <= 0 || inputPrice % TICKET_COST != 0) {
			throw new IllegalArgumentException("잘못된 구입 금액입니다.");
		}
		this.price = inputPrice;
	}

	public long getPrice() {
		return price;
	}
}
