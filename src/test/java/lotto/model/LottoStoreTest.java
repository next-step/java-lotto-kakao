package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoStoreTest {

    @Test
    @DisplayName("최대 구매 가능 개수 계산")
    public void getMaxPurchasable() {
        LottoStore store = new LottoStore();

        assertThat(store.getMaxPurchasable(new Money(14000))).isEqualTo(14);
        assertThat(store.getMaxPurchasable(new Money(14500))).isEqualTo(14);
        assertThat(store.getMaxPurchasable(new Money(1000))).isEqualTo(1);
    }

    @Test
    @DisplayName("수동 구매 개수 검증 - 정상")
    public void validateManualCountSuccess() {
        LottoStore store = new LottoStore();

        assertThatCode(() -> store.validateManualCount(new Money(14000), 14))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("수동 구매 개수 검증 - 초과시 예외")
    public void validateManualCountExceed() {
        LottoStore store = new LottoStore();

        assertThatThrownBy(() -> store.validateManualCount(new Money(14000), 15))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("14장까지만 구매 가능");
    }

    @Test
    @DisplayName("수동 구매")
    public void buyManual() {
        LottoStore store = new LottoStore();
        Wallet wallet = new Wallet(3000);

        List<String> numbersList = List.of("1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12");
        LottoTickets tickets = store.buyManual(wallet, numbersList);

        assertThat(tickets.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("자동 구매 - 잔액만큼 구매")
    public void buyAutoAllIn() {
        LottoStore store = new LottoStore();
        Wallet wallet = new Wallet(5000);

        LottoTickets tickets = store.buyAutoAllIn(wallet);

        assertThat(tickets.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("자동 구매 - 잔액 부족시 빈 티켓")
    public void buyAutoAllInNotEnough() {
        LottoStore store = new LottoStore();
        Wallet wallet = new Wallet(500);

        LottoTickets tickets = store.buyAutoAllIn(wallet);

        assertThat(tickets.size()).isEqualTo(0);
    }
}
