package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoTicketsTest {

    @Test
    @DisplayName("제대로 생성되는지")
    public void createLottoTickets() {
        assertThatCode(() -> new LottoTickets(List.of(
                new LottoTicket(1,2,3,4,5,6)
        ))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("티켓 합치기")
    public void addAll() {
        LottoTickets tickets1 = new LottoTickets();
        tickets1.insertTicket(new LottoTicket(1,2,3,4,5,6));
        
        LottoTickets tickets2 = new LottoTickets();
        tickets2.insertTicket(new LottoTicket(7,8,9,10,11,12));
        
        tickets1.addAll(tickets2);
        
        assertThat(tickets1.size()).isEqualTo(2);
    }
}
