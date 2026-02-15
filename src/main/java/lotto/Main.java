package lotto;

import lotto.domain.pick.AutoLottoNumberGenerator;
import lotto.domain.pick.LottoPickStrategy;
import lotto.view.input.InputView;
import lotto.view.input.TerminalInputView;
import lotto.view.output.OutputView;
import lotto.view.output.TerminalOutputView;

public class Main {
    public static void main(String[] args) {
        InputView input = new TerminalInputView();
        OutputView output = new TerminalOutputView();
        LottoPickStrategy pickStrategy = new AutoLottoNumberGenerator();
        LottoApplication app = new LottoApplication(
                pickStrategy,
                input,
                output
        );

        app.play();
    }
}
