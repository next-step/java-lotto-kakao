package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketBooth {

    public List<Ticket> issueTickets(int price) {
        issueTicketsValidation(price);
        int ticketCount = price / 1000;
        List<Ticket> result = new ArrayList<>();
        while (result.size() < ticketCount) {
            Ticket ticket = issueTicket();
            result.add(ticket);
        }
        return result;
    }

    private Ticket issueTicket() {
        List<Integer> number = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            number.add(i);
        }
        Collections.shuffle(number);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            result.add(number.get(i));
        }
        return new Ticket(result);
    }

    private void issueTicketsValidation(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("음수로는 구매할 수 없습니다.");
        }
        if (price == 0) {
            throw new IllegalArgumentException("0원으로는 구매할 수 없습니다.");
        }
        if (price % 1000 != 0) {
            throw new IllegalArgumentException("1000원 단위로 입력해주세요.");
        }
    }
}
