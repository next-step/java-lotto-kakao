package lotto.domain;

public class LottoPurchaseBudget {
    private static final int MAX_BUDGET_LIMIT = 100000;
    private static final int MIN_BUDGET_LIMIT = 0;
    private static final int TICKET_PRICE = 1000;
    private final int budget;

    public LottoPurchaseBudget(int budget) {
        validateBudget(budget);
        this.budget = budget;
    }

    public int getTicketQuantity() {
        return budget / TICKET_PRICE;
    }

    public double calculateProfitRate(long profit) {
        return (double) profit / budget;
    }

    private void validateBudget(int budget) {
        if (budget > MAX_BUDGET_LIMIT) {
            throw new IllegalArgumentException("구매 금액은" + MAX_BUDGET_LIMIT + "원을 초과할 수 없습니다.");
        }

        if (budget <= MIN_BUDGET_LIMIT) {
            throw new IllegalArgumentException("구매 금액은 양의 정수여야 합니다.");
        }

        if (budget % TICKET_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은" + TICKET_PRICE + "의 배수여야 합니다.");
        }
    }
}
