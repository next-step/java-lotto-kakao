package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoSellerTest {

    @Test
    @DisplayName("자동 티켓 판매")
    public void sellAuto(){
        Wallet wallet = new Wallet(new Money(14500));
        LottoSeller seller = new LottoSeller();
        assertThat(seller.sellAuto(wallet))
                .isInstanceOf(LottoTickets.class);
    }

    @Test
    @DisplayName("수동 티켓 판매")
    public void sellManual() {
        Wallet wallet = new Wallet(new Money(5000));
        LottoSeller seller = new LottoSeller();
        LottoTickets manualTickets = new LottoTickets(List.of(new LottoTicket(1,2,3,4,5,6)));
        seller.sellManual(wallet, manualTickets);
        assertThat(wallet.checkBalance(new Money(4000))).isTrue(); // 1000원 차감 확인
    }

    @Test
    @DisplayName("수동 구매 금액 부족 시 예외 발생 (checkPurchasability)")
    public void checkPurchasabilityFail() {
        Wallet wallet = new Wallet(new Money(1000));
        LottoSeller seller = new LottoSeller();
        assertThatThrownBy(() -> seller.checkPurchasability(wallet, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 금액이 보유 금액보다 클 수 없습니다.");
    }
}
