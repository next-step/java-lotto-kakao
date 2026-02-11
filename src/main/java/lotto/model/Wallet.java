package lotto.model;

public class Wallet {

    private Money balance;
    private Money receipt;

    public Wallet(Money balance) {
        if(balance.isDebt()) throw new RuntimeException("잔액은 항상 0원 이상이어야 합니다.");
        this.balance = balance;
        this.receipt = new Money(0);
    }

    public void change(Money money) {
        Money newBalance = balance.sum(money);
        if(newBalance.isDebt()) {
            throw new RuntimeException("잔액은 항상 0원 이상이어야 합니다.");
        }
        this.balance = newBalance;
        receipt = receipt.sum(money);
    }

    public boolean canAfford(Money money){
        Money newBalance = balance.sum(money);
        return !newBalance.isDebt();
    }

    public Double returnRate(Money money) {
        try {
            Double rateOfReturn = -1 * money.division(receipt);
            receipt = new Money(0);
            balance = balance.sum(money);
            return rateOfReturn;
        }catch (ArithmeticException e){
            return 1.0;
        }
    }

}
