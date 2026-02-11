package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AutoMachineTest {

    @Test
    @DisplayName("티켓 하나 발급")
    public void issueTicket(){
        Wallet wallet = new Wallet(3000);
        AutoMachine autoMachine = new AutoMachine();

        assertThat(autoMachine.issue(wallet))
                .isInstanceOf(LottoTicket.class);
    }


    @Test
    @DisplayName("티켓 복수 발급")
    public void allInTicket(){
        Wallet wallet = new Wallet(14500);
        AutoMachine autoMachine = new AutoMachine();
        assertThat(autoMachine.allIn(wallet))
                .isInstanceOf(LottoTickets.class);
    }


}