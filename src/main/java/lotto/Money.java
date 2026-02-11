package lotto;

public class Money {
	private static final long TICKET_COST = 1000;
	private final long price;

	public Money(long inputPrice) {
		validate(inputPrice);
		this.price = inputPrice;

	}

	private void validate(long inputPrice) {
		if (inputPrice <= 0 || inputPrice % TICKET_COST != 0) {
			throw new IllegalArgumentException("잘못된 구입 금액입니다.");
		}
	}

	public long getPrice() {
		return price;
	}

	public long getTicketCount() {
		return price / TICKET_COST;
	}
}
