package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;


class LottoTicketsTest {


    @Test
    @DisplayName("제대로 생성돼는지")
    public void createLottoTickets() {
        assertThatCode(() -> new LottoTickets(List.of(
                new LottoTicket(1,2,3,4,5,6),
                new LottoTicket(1,2,3,4,5,6),
                new LottoTicket(1,2,3,4,5,6),
                new LottoTicket(1,2,3,4,5,6),
                new LottoTicket(1,2,3,4,5,6),
                new LottoTicket(1,2,3,4,5,6)
        ))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("null값이어도 정상 초기화 되는지 검사")
    public void lottoTicketsIsNull() {
        assertThatCode(() -> new LottoTickets(null)).doesNotThrowAnyException();
    }


}