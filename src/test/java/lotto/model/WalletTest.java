package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class WalletTest {

    @Test
    @DisplayName("지갑 선언")
    public void createWallet(){
        assertThatCode(()-> new Wallet(new Money(10000))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("금액 소비")
    public void useBalance(){
        Wallet wallet = new Wallet(10000);
        assertThatCode(()->wallet.change(new Money(-3000))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("잔액부족")
    public void notEnoughBalance(){
        Wallet wallet = new Wallet(1000);
        assertThatThrownBy(()->wallet.change(new Money(-3000)))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("잔액이 부족합니다.");
    }

    @Test
    @DisplayName("잔액체크 성공")
    public void canAfford(){
        Wallet wallet = new Wallet(10000);
        assertThat(wallet.canAfford(new Money(-3000))).isTrue();
    }

    @Test
    @DisplayName("잔액체크 실패")
    public void canAffordNotEnough(){
        Wallet wallet = new Wallet(1000);
        assertThat(wallet.canAfford(new Money(-3000))).isFalse();
    }

    @Test
    @DisplayName("수익률 정상 반환")
    public void rateOfReturn() {
        Wallet wallet = new Wallet(10500);
        wallet.change(new Money(-10000));
        assertThat(wallet.returnRate(new Money(100000))).isEqualTo(10.0);
    }
}