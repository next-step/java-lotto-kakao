package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoMakerTest {

    @Test
    void ticketKeyEqual() {
        Ticket ticket1 = new Ticket(1, 2, 3, 4, 5, 6);
        Ticket ticket2 = new Ticket(6, 5, 4, 3, 2, 1);
        assertThat(ticket1.getKey()).isEqualTo(ticket2.getKey());
    }

    @Test
    void ticketBoothValidatorThrow() {
        TicketBooth ticketBooth = new TicketBooth();

        Integer[] prices = new Integer[]{1050, -123142341, 0};
        for (Integer price : prices) {
            assertThatThrownBy(() -> ticketBooth.issueTickets(price))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void ticketBoothIssue() {
        TicketBooth ticketBooth = new TicketBooth();
        List<Ticket> tickets = ticketBooth.issueTickets(12000);
        assertThat(tickets.size()).isEqualTo(12);
    }

}