package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PriceTest {

    @Test
    @DisplayName("성공 케이스")
    void success() {
        Price price = new Price(1000);

        Assertions.assertThat(price.getPrice()).isEqualTo(1000);
    }

    @Test
    @DisplayName("가격을 토대로 구매할 수 있는 로또의 개수를 구할 수 있다.")
    void success_() {
        Price price = new Price(14000);
        int lottoCount = price.getLottoCount();

        Assertions.assertThat(lottoCount).isEqualTo(14);
    }

    @Test
    @DisplayName("1000원 미만의 입력을 받은 경우 예외처리 할 수 있다.")
    void fail_priceRange() {
        assertThatThrownBy(() -> {
            Price price = new Price(500);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Price.PRICE_UNDER_1000_ERROR);
    }
}
