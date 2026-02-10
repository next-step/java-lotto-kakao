import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class TicketBooth {


	private Ticket issueTicket() {
		List<Integer> number = new ArrayList<>();
		for(int i = 1; i <=45; i++) {
			number.add(i);
		}
		Collections.shuffle(number);
		List<Integer> result = new ArrayList<>();
		for(int i = 0; i< 6; i++) {
			result.add(number.get(i));
		}
		return new Ticket(result);
	}

	public List<Ticket> issueTickets(Integer price) {
		issueTicketsValidation(price);
		int ticketCount = price / 1000;
		List<Ticket> result = new ArrayList<>();
		while(result.size() < ticketCount) {
			Ticket ticket = issueTicket();
			result.add(ticket);
		}
		return result;
	}

	private void issueTicketsValidation(Integer price) {
		if(price < 0) {
			throw new IllegalArgumentException("빚내서 도박은 안돼!");
		}
		if(price == 0) {
			throw new IllegalArgumentException("공짜 좋아하면 대머리!");
		}
		if(price % 1000 != 0) {
			throw new IllegalArgumentException("1000원 단위의 입력이 아니다!");
		}
	}
}
