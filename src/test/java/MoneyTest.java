import domains.Money;
import domains.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domains.Rank.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTest {
    @Test
    public void 구입_금액에_해당하는_로또_발급_수량을_계산한다() {
        Money money = new Money(14243);

        assertEquals(14, money.availableLottoCount());
    }

    @Test
    public void 당첨_결과를_바탕으로_총_수익률을_계산한다() {
        int value = 14000;
        Money money = new Money(value);
        List<Rank> rankList = List.of(FIRST, SECOND);

        // (20억 + 3천만) / 14,000 = 145,000.0
        double rate = money.calculateRate(rankList);

        assertEquals(145000.0d, rate);
    }

    @Test
    public void 구입_금액이_1000원_이하일_때_예외를_반환한다() {
        assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 지불_가능한_금액보다_많은_로또를_구매하려_하면_예외가_발생한다() {
        Money money = new Money(1000);
        int manualCount = 2;           // 2장(2000원) 구매 시도

        assertThatThrownBy(() -> money.validatePurchasable(manualCount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}