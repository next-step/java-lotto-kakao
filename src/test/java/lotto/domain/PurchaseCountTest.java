package lotto.domain;

import lotto.exception.LottoErrorCode;
import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseCountTest {

    @Test
    @DisplayName("총 구매 수량이 음수면 예외 발생")
    void negativeTotalCountTest() {
        assertThatThrownBy(() -> new PurchaseCount(-1, 0))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoErrorCode.NEGATIVE_TOTAL_COUNT.getMessage());
    }

    @Test
    @DisplayName("수동 구매 장수가 전체 구입 장수보다 많으면 예외 발생")
    void exceedManualCountTest() {
        int totalCount = 5;
        int manualCount = 6;

        assertThatThrownBy(() -> new PurchaseCount(totalCount, manualCount))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoErrorCode.EXCEED_MANUAL_COUNT.getMessage());
    }

    @Test
    @DisplayName("수동 구매 장수가 음수면 예외 발생")
    void negativeManualCountTest() {
        assertThatThrownBy(() -> new PurchaseCount(1, -1))
                .isInstanceOf(LottoException.class)
                .hasMessage(LottoErrorCode.NEGATIVE_MANUAL_COUNT.getMessage());
    }

    @Test
    @DisplayName("정상적으로 자동 구매 개수 계산")
    void calculateAutoCountTest() {
        PurchaseCount purchaseCount = new PurchaseCount(10, 3);

        assertThat(purchaseCount.getAutoCount()).isEqualTo(7);
        assertThat(purchaseCount.getManualCount()).isEqualTo(3);
    }
}
