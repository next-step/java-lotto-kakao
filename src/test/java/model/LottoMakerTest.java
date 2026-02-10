package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoMakerTest {

	@Test
	void imTicket() {
		Ticket leftTicket = new Ticket(1,2,3,4,5,6);
		Ticket rightTicket = new Ticket(6,5,4,3,2,1);
		assertThat(leftTicket.getKey()).isEqualTo(rightTicket.getKey());
	}

	@Test
	void ticketBoothValidatorThrow() {
		TicketBooth ticketBooth = new TicketBooth();

		Integer[] prices = new Integer[]{ 1050, -123142341, 0 };
		for(Integer price: prices) {
			assertThatThrownBy(() -> ticketBooth.issueTickets(price))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Test
	void ticketBoothIssue() {
		TicketBooth ticketBooth = new TicketBooth();


		List<Ticket> tickets = ticketBooth.issueTickets(12000);

		/// 티켓들 갯수가 12개여야한다.
		assertThat(tickets.size()).isEqualTo(12);
	}

}