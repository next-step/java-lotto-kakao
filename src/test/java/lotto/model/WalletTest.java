package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class WalletTest {

    @Test
    @DisplayName("지갑 선언")
    public void createWallet() {
        assertThatCode(() -> new Wallet(new Money(10000))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("금액 지출")
    public void spendBalance() {
        Wallet wallet = new Wallet(5000);
        wallet.spend(new Money(3000));

        assertThat(wallet.canAfford(new Money(2000))).isTrue();
        assertThat(wallet.canAfford(new Money(3000))).isFalse();
    }

    @Test
    @DisplayName("지출 시 잔액 부족")
    public void spendNotEnough() {
        Wallet wallet = new Wallet(1000);
        assertThatThrownBy(() -> wallet.spend(new Money(3000)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("잔액이 부족합니다.");
    }

    @Test
    @DisplayName("잔액체크 성공")
    public void canAfford() {
        Wallet wallet = new Wallet(10000);
        assertThat(wallet.canAfford(new Money(3000))).isTrue();
    }

    @Test
    @DisplayName("잔액체크 실패")
    public void canAffordNotEnough() {
        Wallet wallet = new Wallet(1000);
        assertThat(wallet.canAfford(new Money(3000))).isFalse();
    }

    @Test
    @DisplayName("수익률 계산")
    public void returnRate() {
        Wallet wallet = new Wallet(10000);
        wallet.spend(new Money(10000));

        assertThat(wallet.returnRate(new Money(100000))).isEqualTo(10.0);
    }

    @Test
    @DisplayName("수익률 계산 - 투자금 없을 때")
    public void returnRateNoInvestment() {
        Wallet wallet = new Wallet(10000);

        assertThat(wallet.returnRate(new Money(5000))).isEqualTo(1.0);
    }

}
