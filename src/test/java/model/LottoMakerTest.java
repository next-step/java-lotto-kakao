package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<String> prices = new ArrayList<>(Arrays.asList("1050",  "-123142341", "0"));
        for (String price : prices) {
            assertThatThrownBy(() -> new TicketBooth(price))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void ticketBoothIssue() {
        TicketBooth ticketBooth = new TicketBooth("12000");
        List<Ticket> tickets = ticketBooth.getTickets();
        assertThat(tickets.size()).isEqualTo(12);
    }
}