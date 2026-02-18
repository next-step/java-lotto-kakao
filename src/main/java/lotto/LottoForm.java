package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoForm {
    private final List<Lotto> markedLottos = new ArrayList<>();

    public void mark(List<Integer> numbers) {
        markedLottos.add(new Lotto(numbers));
    }

    List<Lotto> manualLottos() {
        return List.copyOf(markedLottos);
    }
}
