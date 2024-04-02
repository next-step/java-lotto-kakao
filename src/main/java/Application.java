import controller.LottoGame;
import enumeration.LottoCondition;
import strategy.AutoLottoNumberStrategy;
import view.ConsoleView;

import java.util.List;

public final class Application {
    public static void main(String[] args) {
        int cash = ConsoleView.getCash();
        LottoGame game = LottoGame.of(cash, AutoLottoNumberStrategy.of(LottoCondition.LENGTH.value()));
        ConsoleView.printBoughtLottosPrompt(game.lottos());
        List<Integer> winningNumbers = ConsoleView.getWinningNumbers();
        int winningBonus = ConsoleView.getWinningBonus();
        game.start(winningNumbers, winningBonus);
        ConsoleView.printStatistics(game);
    }
}
