package controller;

import java.util.List;

import model.*;
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
            int price = ticketBoothView.inputTicketPrice();
            Lottos ticketBooth = new Lottos(price);
            List<Lotto> lottos = ticketBooth.getLottos();
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
            LottoNumbers mainNumbers = gameScoreView.inputWinNumber();
            gameScoreView.showInputBonusBall();
            LottoNumber bonusNumber = gameScoreView.inputBonusBall();
            statBoardRender(new LottoResult(mainNumbers, bonusNumber), lottos);
        } catch (IllegalArgumentException e) {
            gameScoreView.showErrorMessage(e);
            gameScoreRender(lottos);
        }
    }

    private void statBoardRender(LottoResult score, List<Lotto> lottos) {
        StatsBoard statsBoard = new StatsBoard(score, lottos);
        statsBoardView.showStatResult();
        List<Rank> ranks = List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);
        for (Rank rank : ranks) {
            statsBoardView.showWinCountMessage(rank, statsBoard.getLevelCount(rank));
        }
        statsBoardView.showProfitMessage(statsBoard.getProfitRatio());
    }
}
