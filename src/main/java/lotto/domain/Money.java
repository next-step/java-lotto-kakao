package lotto.domain;

import java.util.Objects;

public class Money {

    public static final long LOTTO_UNIT = 1_000;
    public static final Money ONE_LOTTO_PRICE = Money.won(LOTTO_UNIT);

    public static final String NEGATIVE_MONEY_MSG = "금액은 0 이상이어야 합니다.";
    public static final String INVALID_MONEY_UNIT_MSG = "단위 금액은 0보다 커야 합니다.";

    private final long amount;

    public Money(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(NEGATIVE_MONEY_MSG);
        }
        this.amount = amount;
    }

    public static Money won(long amount) {
        return new Money(amount);
    }

    public long value() {
        return amount;
    }

    // unit 단위로 로또 몇개 살 수 있는지 알려줘
    public LottoCount toLottoCount(Money unit) {
        if (unit.amount <= 0) {
            throw new IllegalArgumentException(INVALID_MONEY_UNIT_MSG);
        }

        return LottoCount.of(Math.toIntExact(amount / unit.amount));
    }

    public double rateOf(Money base) {
        return (double) this.amount / base.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
