package controller;

import java.util.List;

import model.GameScore;
import model.StatsBoard;
import model.Ticket;
import model.TicketBooth;
import model.WinLevel;
import view.GameScoreView;
import view.StatsBoardView;
import view.TicketBoothView;

public class MainController {

    public static final int BALL_COUNT = 6;
    public static final int MIN_BALL_NUMBER = 1;
    public static final int MAX_BALL_NUMBER = 45;

    private final TicketBoothView ticketBoothView = new TicketBoothView();
    private final GameScoreView gameScoreView = new GameScoreView();
    private final StatsBoardView statsBoardView = new StatsBoardView();

    TicketBooth ticketBooth = new TicketBooth();

    public void render() {
        ticketBoothRender();
    }

    private void ticketBoothRender() {
        try {
            ticketBoothView.showInputPriceMessage();
            Integer price = ticketBoothView.inputTicketPrice();
            List<Ticket> tickets = ticketBooth.issueTickets(price);
            ticketBoothView.showTicketInfo(tickets);
            gameScoreRender(tickets);
        } catch (IllegalArgumentException e) {
            ticketBoothView.showErrorMessage(e);
            ticketBoothRender();
        }
    }

    private void gameScoreRender(List<Ticket> tickets) {
        try {
            gameScoreView.showInputWinNumberMessage();
            List<Integer> winNumbers = gameScoreView.inputWinNumber(BALL_COUNT, MIN_BALL_NUMBER, MAX_BALL_NUMBER);
            gameScoreView.showInputBonusBall();
            Integer bonusBall = gameScoreView.inputBonusBall(MIN_BALL_NUMBER, MAX_BALL_NUMBER);
            statBoardRender(new GameScore(bonusBall, winNumbers), tickets);
        } catch (IllegalArgumentException e) {
            gameScoreView.showErrorMessage(e);
            gameScoreRender(tickets);
        }
    }

    private void statBoardRender(GameScore score, List<Ticket> tickets) {
        StatsBoard statsBoard = new StatsBoard(score, tickets);
        statsBoardView.showStatResult();
        List<WinLevel> winLevels = List.of(WinLevel.FIFTH, WinLevel.FOURTH, WinLevel.THIRD, WinLevel.SECOND, WinLevel.FIRST);
        for (WinLevel winLevel : winLevels) {
            statsBoardView.showWinCountMessage(winLevel, statsBoard.getLevelCount(winLevel));
        }
        statsBoardView.showProfitMessage(statsBoard.getProfitRatio());
    }
}
