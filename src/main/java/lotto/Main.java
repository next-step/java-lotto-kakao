package lotto;

import lotto.domain.pick.AutoLottoNumberGenerator;
import lotto.domain.service.AutoLottoService;
import lotto.domain.service.ManualLottoService;
import lotto.view.input.InputView;
import lotto.view.input.TerminalInputView;
import lotto.view.output.OutputView;
import lotto.view.output.TerminalOutputView;

public class Main {
    public static void main(String[] args) {
        InputView input = new TerminalInputView();
        OutputView output = new TerminalOutputView();

        AutoLottoNumberGenerator autoPickStrategy = new AutoLottoNumberGenerator();
        ManualLottoNumberGenerator manualPickStrategy = new ManualLottoNumberGenerator(input);

        ManualLottoService manualLottoService = new ManualLottoService(
                autoPickStrategy,
                manualPickStrategy,
                input,
                output
        );

        AutoLottoService autoLottoService = new AutoLottoService(
                autoPickStrategy,
                input,
                output
        );

        LottoApplication app = new LottoApplication(
                autoLottoService, // 필요에 따라 manual로 갈아 끼운다.
                input,
                output
        );
        app.play();
    }
}
