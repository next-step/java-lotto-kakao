package lotto.generator;

import lotto.domain.Lotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// leaf 클래스
public class ManualLottoGenerator implements LottoGenerator {

    private final InputView inputView;

    public ManualLottoGenerator(InputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public List<Lotto> generate(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(makeLotto());
        }
        return lottos;
    }

    private Lotto makeLotto() {
        String input = inputView.inputManualLotto();
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        return new Lotto(numbers);
    }
}
