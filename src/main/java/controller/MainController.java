package controller;

import java.util.List;

import model.GameScore;
import model.StatsBoard;
import model.Lotto;
import model.Lottos;
import model.WinLevel;
import view.GameScoreView;
import view.StatsBoardView;
import view.TicketBoothView;

public class MainController {

    private static final int BALL_COUNT = 6;
    private static final int MIN_BALL_NUMBER = 1;
    private static final int MAX_BALL_NUMBER = 45;

    private final TicketBoothView ticketBoothView = new TicketBoothView();
    private final GameScoreView gameScoreView = new GameScoreView();
    private final StatsBoardView statsBoardView = new StatsBoardView();

    public void render() {
        ticketBoothRender();
    }

    private void ticketBoothRender() {
        try {
            ticketBoothView.showInputPriceMessage();
            String price = ticketBoothView.inputTicketPrice();
            Lottos ticketBooth = new Lottos(price);
            List<Lotto> lottos = ticketBooth.getTickets();
            ticketBoothView.showTicketInfo(lottos);
            gameScoreRender(lottos);
        } catch (IllegalArgumentException e) {
            ticketBoothView.showErrorMessage(e);
            ticketBoothRender();
        }
    }

    private void gameScoreRender(List<Lotto> lottos) {
        try {
            gameScoreView.showInputWinNumberMessage();
            List<Integer> winNumbers = gameScoreView.inputWinNumber(BALL_COUNT, MIN_BALL_NUMBER, MAX_BALL_NUMBER);
            gameScoreView.showInputBonusBall();
            int bonusBall = gameScoreView.inputBonusBall(MIN_BALL_NUMBER, MAX_BALL_NUMBER);
            statBoardRender(new GameScore(bonusBall, winNumbers), lottos);
        } catch (IllegalArgumentException e) {
            gameScoreView.showErrorMessage(e);
            gameScoreRender(lottos);
        }
    }

    private void statBoardRender(GameScore score, List<Lotto> lottos) {
        StatsBoard statsBoard = new StatsBoard(score, lottos);
        statsBoardView.showStatResult();
        List<WinLevel> winLevels = List.of(WinLevel.FIFTH, WinLevel.FOURTH, WinLevel.THIRD, WinLevel.SECOND, WinLevel.FIRST);
        for (WinLevel winLevel : winLevels) {
            statsBoardView.showWinCountMessage(winLevel, statsBoard.getLevelCount(winLevel));
        }
        statsBoardView.showProfitMessage(statsBoard.getProfitRatio());
    }
}
