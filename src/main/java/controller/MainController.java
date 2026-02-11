package controller;

import java.util.List;

import model.GameScore;
import model.StatsBoard;
import model.Ticket;
import model.TicketBooth;
import model.WinLevel;
import view.GameScoreView;
import view.StatBoardView;
import view.TicketBoothView;

public class MainController {
	TicketBooth ticketBooth = new TicketBooth();
	private TicketBoothView ticketBoothView = new TicketBoothView();
	private GameScoreView gameScoreView = new GameScoreView();
	private StatBoardView statBoardView = new StatBoardView();
	
	public void render() {
		List<Ticket> tickets = ticketBoothRender();
		GameScore gameScore = gameScoreRender();
		statBoardRender(gameScore, tickets);
	}

	private List<Ticket> ticketBoothRender() {
		ticketBoothView.showInputPriceMessage();
		Integer price = ticketBoothView.inputTicketPrice();
		List<Ticket> tickets = ticketBooth.issueTickets(price);
		ticketBoothView.showTicketInfo(tickets);
		return tickets;
	}
	private GameScore gameScoreRender() {
		gameScoreView.showInputWinNumberMessage();
		List<Integer> winNumbers = gameScoreView.inputWinNumber();
		gameScoreView.showInputBonusBall();
		Integer bonusBall = gameScoreView.inputBonusBall();
		return new GameScore(bonusBall, winNumbers);
	}

	private void statBoardRender(GameScore score, List<Ticket> tickets) {
		StatsBoard statsBoard = new StatsBoard(score, tickets);
		statBoardView.showStatResult();
		for(WinLevel winLevel: WinLevel.getAll()) {
			statBoardView.showWinCountMessage(winLevel, statsBoard.getLevelCount(winLevel));
		}
		statBoardView.showProfitMessage(statsBoard.getProfitRatio());
	}
}
