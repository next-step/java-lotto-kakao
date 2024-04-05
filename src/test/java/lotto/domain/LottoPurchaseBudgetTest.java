package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoPurchaseBudgetTest {
    @Test
    public void 구매_금액은_10만원_이하여야_한다() {
        LottoPurchaseBudget budget = new LottoPurchaseBudget(99000);
        Assertions.assertThat(budget).isInstanceOf(LottoPurchaseBudget.class);
    }
    @Test
    public void 구매_금액이_10만원을_초과하면_예외를_던진다() {
        Assertions.assertThatThrownBy(() -> new LottoPurchaseBudget(101000)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void 구매_금액이_음수일_경우_예외를_던진다() {
        Assertions.assertThatThrownBy(() -> new LottoPurchaseBudget(0)).isInstanceOf(RuntimeException.class);
        Assertions.assertThatThrownBy(() -> new LottoPurchaseBudget(-1000)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void 구매_금액이_1000의_배수가_아니면_예외를_던진다() {
        Assertions.assertThatThrownBy(() -> new LottoPurchaseBudget(99999)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void 구매_가능한_티켓의_수를_반환한다() {
        LottoPurchaseBudget budget = new LottoPurchaseBudget(15000);
        int quantity = budget.getTicketQuantity();
        Assertions.assertThat(quantity).isEqualTo(15);
    }

    @Test
    void 수익률을_반환한다() {
        //given
        LottoPurchaseBudget budget = new LottoPurchaseBudget(10000);
        long profit = 5000;

        //when
        double profitRate = budget.calculateProfitRate(profit);

        //then
        Assertions.assertThat(profitRate).isEqualTo(0.50);
    }

    @Test
    void 로또를_구매시_구입비용만큼_예산이_감소한다() {
        //given
        LottoPurchaseBudget budget = new LottoPurchaseBudget(5000);

        //when
        budget.purchaseLotto(3);

        //then
        Assertions.assertThat(budget.getTicketQuantity()).isEqualTo(2);
    }
    @Test
    void 구입하려는_로또_개수가_예산을_넘어가면_예산을_차감하지_않고_예외를_던진다() {
        LottoPurchaseBudget budget = new LottoPurchaseBudget(5000);
        Assertions.assertThatThrownBy(() -> budget.purchaseLotto(6))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 구입하려는_로또_개수가_예산을_넘어가면_예외를_던진다() {
        LottoPurchaseBudget budget = new LottoPurchaseBudget(5000);
        Assertions.assertThatThrownBy(() -> budget.validatePurchasable(6))
                .isInstanceOf(RuntimeException.class);
    }

}
