package level1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PurchaseAmountTest {

    @ParameterizedTest
    @CsvSource({
            "14000, 3, 14, 11",
            "10000, 10, 10, 0",
            "5000, 0, 5, 5"
    })
    @DisplayName("구입 금액과 수동 개수를 입력하면 전체 개수와 자동 개수가 올바르게 계산된다.")
    void calculateCountSuccess(int priceValue, int manualCount, int expectedTotal, int expectedAuto) {
        Price price = new Price(priceValue);

        PurchaseAmount purchaseAmount = new PurchaseAmount(price, manualCount);

        assertThat(purchaseAmount.getManual()).isEqualTo(manualCount);
        assertThat(purchaseAmount.getAuto()).isEqualTo(expectedAuto);
    }

    @Test
    @DisplayName("구매 가능 수량보다 많은 수동 수량을 입력하면 예외가 발생한다.")
    void validateOverTotalCount() {
        Price price = new Price(10000);
        int manualCount = 11;

        assertThatThrownBy(() -> new PurchaseAmount(price, manualCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수동 수량으로 음수를 입력하면 예외가 발생한다.")
    void validateNegativeCount() {
        Price price = new Price(5000);
        int manualCount = -1;

        assertThatThrownBy(() -> new PurchaseAmount(price, manualCount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}