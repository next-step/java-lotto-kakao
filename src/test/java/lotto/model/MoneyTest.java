package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class MoneyTest {

    @Test
    @DisplayName("올바른 금액일 떄")
    public void validateMoney(){
        assertThatCode(() -> new Money(1000));

    }

    @Test
    @DisplayName("합연산")
    public void sum(){
        Money money1 = new Money(1000);
        Money money2 = new Money(2000);
        assertThat(money1.sum(money2)).isEqualTo(new Money(3000));
    }

    @Test
    @DisplayName("나눗셈 연산")
    public void division() {
        Money money1 = new Money(10000);
        Money money2 = new Money(5000);
        assertThat(money1.division(money2)).isEqualTo(2.0);
    }

    @Test
    @DisplayName("0으로 나눌 때 예외 발생")
    public void divisionByZero() {
        Money money1 = new Money(10000);
        Money zero = new Money(0);
        assertThatThrownBy(() -> money1.division(zero))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("0으로 나눌 수 없습니다.");
    }

}