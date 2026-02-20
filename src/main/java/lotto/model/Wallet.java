package lotto.model;

public class Wallet {

    private Money balance;
    private Money receipt;

    public Wallet(int balance) {
        this(new Money(balance));
    }

    public Wallet(Money balance) {
        if (balance.isDebt()) throw new IllegalArgumentException("잔액은 항상 0원 이상이어야 합니다.");
        this.balance = balance;
        this.receipt = new Money(0);
    }

    public void spend(Money money) {
        Money newBalance = balance.subtract(money);
        if (newBalance.isDebt()) {
            throw new IllegalStateException("잔액이 부족합니다.");
        }
        this.balance = newBalance;
        receipt = receipt.sum(money);
    }

    public boolean canAfford(Money money) {
        return !balance.subtract(money).isDebt();
    }

    public Double returnRate(Money money) {
        try {
            return money.division(receipt);
        } catch (ArithmeticException e) {
            return 1.0;
        }
    }

}
