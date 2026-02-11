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
	public static final int MAX_BALL_NUMBER = 45;
	public static final int MIN_BALL_NUMBER = 1;
	TicketBooth ticketBooth = new TicketBooth();
	private final TicketBoothView ticketBoothView = new TicketBoothView();
	private final GameScoreView gameScoreView = new GameScoreView();
	private final StatBoardView statBoardView = new StatBoardView();

	public void render() {
			ticketBoothRender();
	}

	private void ticketBoothRender() {
		ticketBoothView.showInputPriceMessage();
		Integer price = ticketBoothView.inputTicketPrice();
		List<Ticket> tickets = ticketBooth.issueTickets(price);
		ticketBoothView.showTicketInfo(tickets);
		gameScoreRender(tickets);
	}

	private void gameScoreRender(List<Ticket> tickets) {
		gameScoreView.showInputWinNumberMessage();

		List<Integer> winNumbers = gameScoreView.inputWinNumber();
		if(winNumbers.size() != 6) {
			gameScoreView.showErrorMessage(new IllegalArgumentException("숫자 6개를 입력해주세요"));
			gameScoreRender(tickets);
			return;
		}

		for (Integer winNumber : winNumbers) {
			if(winNumber < MIN_BALL_NUMBER || winNumber > MAX_BALL_NUMBER) {
				gameScoreView.showErrorMessage(new IllegalArgumentException("1부터 45까지를 입력하세요"));
				gameScoreRender(tickets);
				return;
			}
		}

		gameScoreView.showInputBonusBall();
		Integer bonusBall = gameScoreView.inputBonusBall();
		if(bonusBall < MIN_BALL_NUMBER || bonusBall > MAX_BALL_NUMBER) {
			gameScoreView.showErrorMessage(new IllegalArgumentException("1부터 45까지를 입력하세요"));
			gameScoreRender(tickets);
			return;
		}

		statBoardRender(new GameScore(bonusBall, winNumbers), tickets);
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
