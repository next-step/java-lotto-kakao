package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoForm {
    private final List<Lotto> markedLottos = new ArrayList<>();

    void mark(List<Integer> numbers) {
        markedLottos.add(new Lotto(numbers));
    }

    LottoBundle submit() {
        return new LottoBundle(markedLottos);
    }
}
