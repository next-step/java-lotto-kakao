package com.kakao.onboarding.precourse.albusduke.lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MoneyTest {

    @Test
    void 금액을_생성할_수_있다() {
        Money money = new Money(1000);

        assertThat(money.getAmount()).isEqualTo(1000);
    }

    @Test
    void 금액_0으로_생성할_수_있다() {
        Money money = new Money(0);

        assertThat(money.getAmount()).isEqualTo(0);
    }

    @Test
    void 음수_금액으로_생성할_수_없다() {
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액은 0 이상이어야 합니다.");
    }

    @Test
    void 큰_금액으로_생성할_수_있다() {
        Money money = new Money(100_000_000);

        assertThat(money.getAmount()).isEqualTo(100_000_000);
    }

    @Test
    void 금액을_나눌_수_있다() {
        Money money = new Money(5000);
        Money divisor = new Money(1000);

        assertThat(money.divide(divisor)).isEqualTo(5);
    }

    @Test
    void 나누기_결과가_정수_몫으로_계산된다() {
        Money money = new Money(3500);
        Money divisor = new Money(1000);

        assertThat(money.divide(divisor)).isEqualTo(3);
    }

    @Test
    void 금액_0으로_나누면_예외가_발생한다() {
        Money money = new Money(1000);
        Money zero = new Money(0);

        assertThatThrownBy(() -> money.divide(zero))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0으로 나눌 수 없습니다.");
    }

    @Test
    void 같은_금액으로_나누면_1을_반환한다() {
        Money money = new Money(1000);

        assertThat(money.divide(money)).isEqualTo(1);
    }

    @Test
    void 금액이_나누는_금액보다_작으면_0을_반환한다() {
        Money money = new Money(500);
        Money divisor = new Money(1000);

        assertThat(money.divide(divisor)).isEqualTo(0);
    }
}
