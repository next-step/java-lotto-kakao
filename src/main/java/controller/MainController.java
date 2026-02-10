package controller;

import java.util.List;

import model.Ticket;
import model.TicketBooth;
import view.TicketBoothView;

public class MainController {
	TicketBoothView ticketBoothView = new TicketBoothView();
	TicketBooth ticketBooth = new TicketBooth();
	public void render() {
		ticketBoothView.showInputPriceMessage();
		Integer price = ticketBoothView.inputTicketPrice();
		List<Ticket> tickets = ticketBooth.issueTickets(price);
		ticketBoothView.showTicketInfo(tickets);
	}
}
