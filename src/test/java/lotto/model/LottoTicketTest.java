package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoTicketTest {

    @Test
    @DisplayName("정상적인 로또 생성")
    public void createTicket(){
        assertThatCode(()->new LottoTicket(1,2,3,4,5,6)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 번호 개수가 부족할 때")
    public void notEnoughNumber(){
        assertThatThrownBy(() -> new LottoTicket(1,2,3,4,5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓에는 6개의 번호가 필요합니다.");
    }

    @Test
    @DisplayName("로또 번호에 중복이 있을 때")
    public void duplicateNumber(){
        assertThatThrownBy(() -> new LottoTicket(1,2,3,4,3,6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓에는 6개의 번호가 필요합니다.");
    }

    @Test
    @DisplayName("티켓에 번호가 포함되어 있을 때")
    public void containsNumber() {
        LottoTicket ticket = new LottoTicket(1, 2, 3, 4, 5, 6);
        assertThat(ticket.contains(new LottoNumber(3))).isTrue();
    }

    @Test
    @DisplayName("티켓에 번호가 포함되어 있지 않을 때")
    public void notContainsNumber() {
        LottoTicket ticket = new LottoTicket(1, 2, 3, 4, 5, 6);
        assertThat(ticket.contains(new LottoNumber(7))).isFalse();
    }

    @Test
    @DisplayName("두 티켓의 일치 개수 - 전부 일치")
    public void matchCountAll() {
        LottoTicket ticket1 = new LottoTicket(1, 2, 3, 4, 5, 6);
        LottoTicket ticket2 = new LottoTicket(1, 2, 3, 4, 5, 6);
        assertThat(ticket1.matchCount(ticket2)).isEqualTo(6);
    }

    @Test
    @DisplayName("두 티켓의 일치 개수 - 일부 일치")
    public void matchCountPartial() {
        LottoTicket ticket1 = new LottoTicket(1, 2, 3, 4, 5, 6);
        LottoTicket ticket2 = new LottoTicket(1, 2, 3, 7, 8, 9);
        assertThat(ticket1.matchCount(ticket2)).isEqualTo(3);
    }

    @Test
    @DisplayName("두 티켓의 일치 개수 - 불일치")
    public void matchCountNone() {
        LottoTicket ticket1 = new LottoTicket(1, 2, 3, 4, 5, 6);
        LottoTicket ticket2 = new LottoTicket(7, 8, 9, 10, 11, 12);
        assertThat(ticket1.matchCount(ticket2)).isEqualTo(0);
    }

}