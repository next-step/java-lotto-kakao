package lotto;

public class Money {
	private static final long TICKET_COST = 1000;
	private final long price;

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

	public void validateManualLottoCount(int manualCount) {
		long totalCount = price / TICKET_COST;
		if (manualCount < 0 || manualCount > totalCount) {
			throw new IllegalArgumentException(
				String.format("수동 로또 개수는 0 이상 %d 이하여야 합니다.", totalCount)
			);
		}
	}

}
