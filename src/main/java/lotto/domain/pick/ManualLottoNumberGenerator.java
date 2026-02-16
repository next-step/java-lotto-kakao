package lotto.domain.pick;

import lotto.domain.LottoNumber;
import lotto.view.input.InputView;

import java.util.List;

public class ManualLottoNumberGenerator implements LottoPickStrategy {

    private final InputView inputView;

    public ManualLottoNumberGenerator(final InputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public List<LottoNumber> generate() {
        return inputView.inputNumbers(",").stream()
                .map(LottoNumber::of)
                .toList();
    }

}
