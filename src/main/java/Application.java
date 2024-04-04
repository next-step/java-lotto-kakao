import controller.LottoGame;
import domain.Lotto;
import domain.LottoMachine;
import strategy.AutoLottoNumberStrategy;

import java.util.List;

import static view.ConsoleView.*;

public final class Application {
    public static void main(String[] args) {
        LottoGame game = lottoGame();
        printBoughtLottosPrompt(game);
        game.start(winningNumbers(), winningBonus());
        printStatistics(game);
    }

    private static LottoGame lottoGame() {
        return LottoGame.of(
                LottoMachine.issue(
                        LottoMachine.bunchSize(getCash()),
                        AutoLottoNumberStrategy.of(Lotto.LENGTH)
                )
        );
    }

    private static List<Integer> winningNumbers() {
        return getWinningNumbers();
    }

    private static int winningBonus() {
        return getWinningBonus();
    }
}
