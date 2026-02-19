package lotto;

import lotto.domain.pick.AutoLottoNumberGenerator;
import lotto.domain.pick.LottoPickStrategy;
import lotto.domain.service.AutoLottoService;
import lotto.domain.service.LottoService;
import lotto.domain.service.ManualLottoService;
import lotto.view.input.TerminalInputView;
import lotto.view.output.TerminalOutputView;

public class LottoConfig {

    public LottoApplication manualApp() {
        TerminalInputView input = new TerminalInputView();
        TerminalOutputView output = new TerminalOutputView();

        LottoPickStrategy autoStrategy = new AutoLottoNumberGenerator();
        LottoService service = new ManualLottoService(autoStrategy);

        return new LottoApplication(service, input, output);
    }

    public LottoApplication autoApp() {
        TerminalInputView input = new TerminalInputView();
        TerminalOutputView output = new TerminalOutputView();

        LottoPickStrategy autoStrategy = new AutoLottoNumberGenerator();
        LottoService service = new AutoLottoService(autoStrategy);

        return new LottoApplication(service, input, output);
    }

}
