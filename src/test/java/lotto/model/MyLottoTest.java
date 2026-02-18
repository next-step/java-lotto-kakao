package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class MyLottoTest {

    @Test
    @DisplayName("생성 테스트")
    public void createMyLotto() {
        assertThatCode(() -> new MyLotto()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("티켓 추가 확인")
    public void addTickets() {
        MyLotto myLotto = new MyLotto();
        LottoTickets tickets = new LottoTickets(List.of(new LottoTicket(1,2,3,4,5,6)));
        myLotto.addTickets(tickets);
        assertThat(myLotto.getAllTickets().size()).isEqualTo(1);
    }
}