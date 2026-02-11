package view;

import java.util.List;
import java.util.Scanner;

import model.Ticket;

public class TicketBoothView {

    private final Scanner SCANNER = new Scanner(System.in);

    public void showInputPriceMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public int inputTicketPrice() {
        String input = SCANNER.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 입력입니다. 1000원 단위의 숫자로만 입력해 주세요.");
        }
    }

    public void showTicketInfo(List<Ticket> tickets) {
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[Error] " + e.getMessage());
    }
}
