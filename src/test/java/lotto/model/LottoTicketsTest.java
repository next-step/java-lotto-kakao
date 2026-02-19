package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


class LottoTicketsTest {


    @Test
    @DisplayName("제대로 생성돼는지")
    public void createLottoTickets() {
        assertThatCode(() -> new LottoTickets(List.of(
                new LottoTicket(1, 2, 3, 4, 5, 6),
                new LottoTicket(1, 2, 3, 4, 5, 6),
                new LottoTicket(1, 2, 3, 4, 5, 6),
                new LottoTicket(1, 2, 3, 4, 5, 6),
                new LottoTicket(1, 2, 3, 4, 5, 6),
                new LottoTicket(1, 2, 3, 4, 5, 6)
        ))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("null값이어도 정상 초기화 되는지 검사")
    public void lottoTicketsIsNull() {
        assertThatCode(() -> new LottoTickets(null)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("티켓 추가")
    public void add() {
        LottoTickets tickets = new LottoTickets();
        tickets.add(new LottoTicket(1, 2, 3, 4, 5, 6));

        assertThat(tickets.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("티켓 합치기")
    public void mergeTickets() {
        LottoTickets tickets1 = new LottoTickets(List.of(new LottoTicket(1, 2, 3, 4, 5, 6)));
        LottoTickets tickets2 = new LottoTickets(List.of(new LottoTicket(7, 8, 9, 10, 11, 12)));

        tickets1.merge(tickets2);

        assertThat(tickets1.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("당첨 결과 집계")
    public void result() {
        LottoTickets tickets = new LottoTickets(List.of(
                new LottoTicket(1, 2, 3, 4, 5, 6),
                new LottoTicket(1, 2, 3, 4, 5, 7)
        ));
        WinningLotto winningLotto = new WinningLotto(
                new LottoTicket(1, 2, 3, 4, 5, 6),
                LottoNumber.of(7)
        );

        WinningInfo info = tickets.result(winningLotto);

        Money expectedTotal = WinningRank.FIRST.winningPrice.sum(WinningRank.SECOND.winningPrice);
        assertThat(info.totalPrice()).isEqualTo(expectedTotal);
    }


}
