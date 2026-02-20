package lotto.model;

public class Money {

    private final int value;

    public Money(int money) {
        this.value = money;
    }

    public boolean isDebt() {
        return value < 0;
    }

    public Money sum(Money m2) {
        return new Money(this.value + m2.value);
    }

    public Money subtract(Money m2) {
        return new Money(this.value - m2.value);
    }

    public Double division(Money m2) {
        if (m2.value == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return (double) this.value / m2.value;
    }

    public Money multiple(int count) {
        return new Money(value * count);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Money)) return false;
        Money money = (Money) obj;
        return this.value == money.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return value + "원";
    }

}
