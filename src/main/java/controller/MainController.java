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
	private final TicketBoothView ticketBoothView = new TicketBoothView();
	private final GameScoreView gameScoreView = new GameScoreView();
	private final StatBoardView statBoardView = new StatBoardView();

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
		List<WinLevel> winLevels = List.of(WinLevel.FIFTH, WinLevel.FOURTH, WinLevel.THIRD, WinLevel.SECOND, WinLevel.FIRST);
		for(WinLevel winLevel: winLevels) {
			statBoardView.showWinCountMessage(winLevel, statsBoard.getLevelCount(winLevel));
		}
		statBoardView.showProfitMessage(statsBoard.getProfitRatio());
	}
}
