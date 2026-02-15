package lotto.domain.pick;

import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.List;

public class ManualLottoNumberGenerator implements LottoPickStrategy {

    private final InputView inputView;
    private final OutputView outputView;

    public ManualLottoNumberGenerator(
            final InputView inputView,
            final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public List<Integer> generate() {
        outputView.printMessage("수동으로 구매할 번호를 입력해 주세요.");
        return inputView.inputNumbers(",");
    }
}
