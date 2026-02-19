package domains;

import java.util.List;

public class Money {
    private final int amount;
    private final static int LOTTO_PRICE = 1000;

    public Money(int amount) {
        validate(amount);

        this.amount = amount;
    }

    private void validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("돈은 음수일 수 없습니다.");
        }

        if (value < LOTTO_PRICE) {
            throw new IllegalArgumentException(String.format("구입 금액은 최소 %d원 이상이어야 합니다.", LOTTO_PRICE));
        }
    }

    public void validatePurchasable(int count) {
        if(count < 0) {
            throw new IllegalArgumentException("구매 횟수는 양수여야 합니다.");
        }

        if (amount < count * LOTTO_PRICE) {
            throw new IllegalArgumentException("지불할 금액보다 구매 횟수가 더 많습니다.");
        }
    }

    public Integer availableLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public double calculateRate(List<Rank> rankList) {
        if (amount == 0) return  0.0d;

        long totalWinningMoney = rankList.stream()
                .mapToLong(Rank::getWinningMoney)
                .sum();

        return (double) totalWinningMoney / amount;
    }
}