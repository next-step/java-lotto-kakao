package lotto.domain.pick;

import lotto.view.input.InputView;

import java.util.List;

public class ManualLottoNumberGenerator implements LottoPickStrategy {

    private final InputView inputView;

    public ManualLottoNumberGenerator(final InputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public List<Integer> generate() {
        return inputView.inputNumbers(",");
    }
}
