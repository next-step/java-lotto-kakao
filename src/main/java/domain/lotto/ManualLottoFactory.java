package domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoFactory implements LottoFactory {

    private final List<List<Integer>> lottosNumbers;

    public ManualLottoFactory(List<List<Integer>> lottosNumbers) {
        this.lottosNumbers = lottosNumbers;
    }

    @Override
    public List<Lotto> create() {
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> numbers : lottosNumbers) {
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }
        return lottos;
    }
}
