package controller;

import java.util.List;

import model.GameInfo;
import model.StatsBoard;
import model.Ticket;
import model.TicketBooth;
import model.WinLevel;
import view.GameScoreView;
import view.StatBoardView;
import view.TicketBoothView;

public class MainController {
	TicketBoothView ticketBoothView = new TicketBoothView();
	TicketBooth ticketBooth = new TicketBooth();
	GameScoreView gameScoreView = new GameScoreView();
	StatBoardView statBoardView = new StatBoardView();
	public void render() {
		ticketBoothView.showInputPriceMessage();
		Integer price = ticketBoothView.inputTicketPrice();
		List<Ticket> tickets = ticketBooth.issueTickets(price);
		ticketBoothView.showTicketInfo(tickets);

		gameScoreView.showInputWinNumberMessage();
		List<Integer> winNumbers = gameScoreView.inputWinNumber();
		gameScoreView.showInputBonusBall();
		Integer bonusBall = gameScoreView.inputBonusBall();
		GameInfo info = new GameInfo(bonusBall, winNumbers);

		StatsBoard statsBoard = new StatsBoard(info, tickets);
		statBoardView.showStatResult();
		for(WinLevel winLevel: WinLevel.getAll()) {
			statBoardView.showWinCountMessage(winLevel, statsBoard.getLevelCount(winLevel));
		}
		statBoardView.showProfitMessage(statsBoard.getProfitRatio());
	}
}
