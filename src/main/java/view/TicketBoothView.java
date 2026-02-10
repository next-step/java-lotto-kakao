package view;

import java.util.List;
import java.util.Scanner;

import model.Ticket;

public class TicketBoothView {
	Scanner scanner = new Scanner(System.in);
	void showInputPriceMessage() {
		System.out.println("구입금액을 입력해 주세요.");
	}

	void showTicketInfo(List<Ticket> tickets) {
		System.out.println(tickets.size() + "개를 구매했습니다.");
		for(Ticket ticket: tickets) {
			System.out.println(ticket);
		}
	}


	void showInputWinNumberMessage() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
	}

	void showInputBonusBall() {
		System.out.println("보너스 볼을 입력해 주세요.");
	}

	Integer inputTicketPrice() {
		String input = scanner.nextLine();
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			throw new IllegalArgumentException("잘 못 된 입력");
		}
	}

	void showErrorMessage(IllegalArgumentException e) {
		System.out.println(e.getMessage());
	}
}
