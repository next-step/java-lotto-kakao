import controller.LottoGame;
import domain.LottoMachine;
import enumeration.LottoCondition;
import strategy.AutoLottoNumberStrategy;
import view.ConsoleView;

public final class Application {
    public static void main(String[] args) {
        LottoGame game = LottoGame.of(
                LottoMachine.issue(
                        LottoMachine.bunchSize(ConsoleView.getCash()),
                        AutoLottoNumberStrategy.of(LottoCondition.LENGTH.value())
                )
        );
        ConsoleView.printBoughtLottosPrompt(game.lottos());
        game.start(ConsoleView.getWinningNumbers(), ConsoleView.getWinningBonus());
        ConsoleView.printStatistics(game);
    }
}
