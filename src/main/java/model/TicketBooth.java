package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketBooth {

    private final List<Ticket> tickets;

    public TicketBooth(String rawPrice) {
        int price = validate(rawPrice);
        int ticketCount = price / 1000;
        List<Ticket> tickets = new ArrayList<>();
        while (tickets.size() < ticketCount) {
            Ticket ticket = issueTicket();
            tickets.add(ticket);
        }
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
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

    private int validate(String rawPrice) {
        int price = 0;
        try {
            price = Integer.parseInt(rawPrice);
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("음수로는 구매할 수 없습니다.");
        }
        if (price == 0) {
            throw new IllegalArgumentException("0원으로는 구매할 수 없습니다.");
        }
        if (price % 1000 != 0) {
            throw new IllegalArgumentException("1000원 단위로 입력해주세요.");
        }
        return price;
    }
}
