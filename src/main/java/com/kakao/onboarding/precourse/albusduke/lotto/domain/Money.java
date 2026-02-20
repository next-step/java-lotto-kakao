package com.kakao.onboarding.precourse.albusduke.lotto.domain;

public class Money {
    private static final String INVALID_MONEY_AMOUNT_ERR_MSG = "금액은 0 이상이어야 합니다.";
    private static final String DIVIDED_BY_ZERO_ERR_MSG = "0으로 나눌 수 없습니다.";
    private final int amount;

    public Money(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(INVALID_MONEY_AMOUNT_ERR_MSG);
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int divide(Money money) {
        if (money.amount == 0) {
            throw new IllegalArgumentException(DIVIDED_BY_ZERO_ERR_MSG);
        }
        return amount / money.amount;
    }
}
