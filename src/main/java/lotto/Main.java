package lotto;

import lotto.generator.CompositeLottoGenerator;
import lotto.generator.LottoGenerator;
import lotto.generator.NumberGenerator;
import lotto.generator.RandomNumberGenerator;
import lotto.view.CommandInputView;
import lotto.view.CommandOutputView;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {

    public static void main(String[] args) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        CompositeLottoGenerator lottoGenerator = new CompositeLottoGenerator();
        InputView inputView = new CommandInputView();
        OutputView outputView = new CommandOutputView();

        LottoController controller = new LottoController(numberGenerator, lottoGenerator, inputView, outputView);

        controller.play();
    }
}
