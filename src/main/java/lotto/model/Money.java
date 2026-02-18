package lotto.model;

public class Money {
    private final long value;

    public Money(long money) {
        if (money < 0) {
            throw new RuntimeException("금액은 0원 이상이어야 합니다.");
        }
        this.value = money;
    }

    public Money sum(Money other) {
        return new Money(this.value + other.value);
    }

    public Money minus(Money other) {
        if (this.value < other.value) {
            throw new RuntimeException("잔액이 부족합니다.");
        }
        return new Money(this.value - other.value);
    }

    public Double division(Money other) {
        if (other.value == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return  (double) this.value / other.value;
    }

    public Money multiple(int count){
        return new Money(value * count);
    }

    public long getValue() {
        return value;
    }

    public boolean isLessThan(Money other) {
        return this.value < other.value;
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
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return "Money{value=" + value + "}";
    }
}
